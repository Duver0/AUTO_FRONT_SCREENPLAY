package screenplay.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MedicalBackendClient {

    private static final String DEFAULT_API_BASE_URL = "http://localhost:3000";
    private static final String SIGN_UP_PATH = "/auth/signUp";
    private static final String SIGN_IN_PATH = "/auth/signIn";
    private static final String CREATE_TURNO_PATH = "/turnos";
    private static final String FINALIZAR_ATENCION_PATH = "/medicos/atencion/finalizar";
    private static final String LIBERAR_CONSULTORIO_PATH = "/medicos/consultorio/liberar";

    private static final int HTTP_CREATED = 201;
    private static final int HTTP_OK = 200;
    private static final int HTTP_ACCEPTED = 202;
    private static final int HTTP_CONFLICT = 409;
    private static final int HTTP_BAD_REQUEST = 400;
    private static final int HTTP_NOT_FOUND = 404;

    private static final Pattern TOKEN_PATTERN = Pattern.compile("\\\"token\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"");

    private final HttpClient httpClient;
    private final String apiBaseUrl;

    public MedicalBackendClient() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        this.apiBaseUrl = resolveApiBaseUrl();
    }

    public void registerMedico(String name, String email, String password) {
        String payload = "{"
            + "\"nombre\":\"" + escapeJson(name) + "\","
            + "\"email\":\"" + escapeJson(email) + "\","
            + "\"password\":\"" + escapeJson(password) + "\","
            + "\"rol\":\"medico\""
            + "}";

        HttpResponse<String> response = sendJsonPost(SIGN_UP_PATH, payload, null);
        int status = response.statusCode();
        if (status != HTTP_CREATED && status != HTTP_CONFLICT) {
            throw new IllegalStateException("No fue posible registrar medico de prueba. HTTP "
                    + status + ". Body: " + response.body());
        }
    }

    public void createTurno(String nombrePaciente, long cedulaPaciente) {
        String payload = "{"
            + "\"nombre\":\"" + escapeJson(nombrePaciente) + "\","
            + "\"cedula\":" + cedulaPaciente + ","
            + "\"priority\":\"alta\""
            + "}";

        HttpResponse<String> response = sendJsonPost(CREATE_TURNO_PATH, payload, null);
        if (response.statusCode() != HTTP_ACCEPTED) {
            throw new IllegalStateException("No fue posible crear turno de prueba. HTTP "
                    + response.statusCode() + ". Body: " + response.body());
        }
    }

    public void tryCleanupDoctorSession(String email, String password) {
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            return;
        }

        try {
            String token = signInAndGetToken(email, password);
            if (token == null || token.isBlank()) {
                return;
            }

            sendJsonPost(FINALIZAR_ATENCION_PATH, "{}", token);

            for (int attempt = 1; attempt <= 6; attempt++) {
                HttpResponse<String> releaseResponse = sendJsonPost(LIBERAR_CONSULTORIO_PATH, "{}", token);
                int status = releaseResponse.statusCode();
                if (status == HTTP_ACCEPTED || status == HTTP_BAD_REQUEST || status == HTTP_NOT_FOUND) {
                    return;
                }

                if (status == HTTP_CONFLICT) {
                    Thread.sleep(400);
                    continue;
                }

                return;
            }
        } catch (Exception ignored) {
            // Best-effort cleanup to keep scenarios independent without breaking test flow.
        }
    }

    private String signInAndGetToken(String email, String password) {
        String payload = "{"
            + "\"email\":\"" + escapeJson(email) + "\","
            + "\"password\":\"" + escapeJson(password) + "\""
            + "}";

        HttpResponse<String> response = sendJsonPost(SIGN_IN_PATH, payload, null);
        if (response.statusCode() != HTTP_OK) {
            return null;
        }

        Matcher matcher = TOKEN_PATTERN.matcher(response.body());
        if (!matcher.find()) {
            return null;
        }
        return matcher.group(1);
    }

    private HttpResponse<String> sendJsonPost(String path, String payload, String bearerToken) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(apiBaseUrl + path))
                .timeout(Duration.ofSeconds(20))
                .header("Content-Type", "application/json");

        if (bearerToken != null && !bearerToken.isBlank()) {
            builder.header("Authorization", "Bearer " + bearerToken);
        }

        HttpRequest request = builder.POST(HttpRequest.BodyPublishers.ofString(payload)).build();
        return send(request);
    }

    private HttpResponse<String> send(HttpRequest request) {
        IOException lastException = null;

        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException exception) {
                lastException = exception;
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Ejecucion interrumpida en cliente backend de pruebas", exception);
            }
        }

        throw new IllegalStateException("Error de red ejecutando cliente backend de pruebas", lastException);
    }

    private String resolveApiBaseUrl() {
        String raw = System.getenv("NEXT_PUBLIC_API_BASE_URL");
        if (raw == null || raw.isBlank()) {
            return DEFAULT_API_BASE_URL;
        }

        return raw.endsWith("/") ? raw.substring(0, raw.length() - 1) : raw;
    }

    private String escapeJson(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}
