package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;
import screenplay.actors.ActorFactory;
import screenplay.interactions.RegistrarMedicoDePrueba;
import screenplay.questions.EstadoAtencionPacienteQuestion;
import screenplay.questions.EstadoConsultorioQuestion;
import screenplay.questions.MensajePausaProgramadaQuestion;
import screenplay.questions.PacienteVisibleQuestion;
import screenplay.questions.PausaProgramadaVisibleQuestion;
import screenplay.tasks.AsignarPacienteEnEspera;
import screenplay.tasks.FinalizarAtencion;
import screenplay.tasks.IniciarSesionComoMedico;
import screenplay.tasks.MarcarNoDisponible;
import screenplay.tasks.VincularseAConsultorio;
import screenplay.utils.MedicalBackendClient;
import screenplay.utils.TestMedicalDataFactory;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DisponibilidadYAtencionSteps {

    private static final String LOGIN_BUTTON_LABEL = "Iniciar sesión";
    private static final String TAKE_CONSULTORIO_BUTTON_LABEL = "Tomar consultorio";
    private static final String START_ATTENTION_BUTTON_LABEL = "Iniciar atencion";
    private static final String FINALIZE_ATTENTION_BUTTON_LABEL = "Finalizar atencion";
    private static final String PAUSE_ATTENTION_BUTTON_LABEL = "Pausar atencion";
    private static final String NO_ACTIVE_ATTENTION_STATUS = "Aun no hay atencion activa";
    private static final String CALLED_PATIENT_PENDING_STATUS = "Paciente llamado pendiente de iniciar atencion";

    @Managed(driver = "chrome")
    private WebDriver driver;

    private final MedicalBackendClient backendClient = new MedicalBackendClient();

    private String medicoName;
    private String medicoEmail;
    private String medicoPassword;
    private Actor medicoActor;

    @Before("@disponibilidad_y_atencion")
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        medicoActor = ActorFactory.createActor("Medico", driver);
        medicoName = TestMedicalDataFactory.uniqueName("medico");
        medicoEmail = TestMedicalDataFactory.uniqueEmail("medico");
        medicoPassword = "Duver123--";
    }

    @After("@disponibilidad_y_atencion")
    public void cleanScenarioState() {
        backendClient.tryCleanupDoctorSession(medicoEmail, medicoPassword);
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
    }

    @Given("el medico inicia sesion desde {string} con credenciales validas")
    public void elMedicoIniciaSesionDesdeConCredencialesValidas(String loginButtonLabel) {
        ensureStepLabel(loginButtonLabel, LOGIN_BUTTON_LABEL, "boton de login");

        medicoActor.attemptsTo(
                RegistrarMedicoDePrueba.conDatos(medicoName, medicoEmail, medicoPassword),
                IniciarSesionComoMedico.conCredenciales(medicoEmail, medicoPassword)
        );
    }

    @Given("el medico se vincula con el boton {string} a un consultorio disponible")
    public void elMedicoSeVinculaConElBotonAUnConsultorioDisponible(String takeConsultorioButton) {
        ensureStepLabel(takeConsultorioButton, TAKE_CONSULTORIO_BUTTON_LABEL, "boton de vinculacion");

        medicoActor.attemptsTo(VincularseAConsultorio.alPrimerConsultorioDisponible());
    }

    @Given("el medico lleva el consultorio a {string} usando {string} con un paciente en espera")
    public void elMedicoLlevaElConsultorioAUsandoConUnPacienteEnEspera(String expectedState, String startAttentionButton) {
        ensureStepLabel(expectedState, "En atencion", "estado de atencion activa");
        ensureStepLabel(startAttentionButton, START_ATTENTION_BUTTON_LABEL, "boton de iniciar atencion");

        medicoActor.attemptsTo(AsignarPacienteEnEspera.enElConsultorioActual());
    }

    @When("el medico finaliza la atencion desde {string}")
    public void elMedicoFinalizaLaAtencionDesde(String finalizeAttentionButton) {
        ensureStepLabel(finalizeAttentionButton, FINALIZE_ATTENTION_BUTTON_LABEL, "boton de finalizar atencion");

        medicoActor.attemptsTo(FinalizarAtencion.actual());
    }

    @When("el medico intenta pausar durante atencion desde {string}")
    public void elMedicoIntentaPausarDuranteAtencionDesde(String pauseAttentionButton) {
        ensureStepLabel(pauseAttentionButton, PAUSE_ATTENTION_BUTTON_LABEL, "boton de pausar atencion");

        medicoActor.attemptsTo(MarcarNoDisponible.enAtencionActiva());
    }

    @Then("el estado del consultorio se muestra como {string}")
    public void elEstadoDelConsultorioSeMuestraComo(String expectedConsultorioState) {
        medicoActor.should(
                seeThat(EstadoConsultorioQuestion.value(), equalTo(expectedConsultorioState))
        );
    }

    @Then("el estado de atencion del paciente se muestra como {string}")
    public void elEstadoDeAtencionDelPacienteSeMuestraComo(String expectedAttentionState) {
        if (NO_ACTIVE_ATTENTION_STATUS.equals(expectedAttentionState)) {
            medicoActor.should(
                seeThat(
                    EstadoAtencionPacienteQuestion.value(),
                    org.hamcrest.Matchers.anyOf(
                        equalTo(NO_ACTIVE_ATTENTION_STATUS),
                        equalTo(CALLED_PATIENT_PENDING_STATUS)
                    )
                )
            );
            return;
        }

        medicoActor.should(
                seeThat(EstadoAtencionPacienteQuestion.value(), equalTo(expectedAttentionState))
        );
    }

    @Then("no hay paciente activo visible en el consultorio")
    public void noHayPacienteActivoVisibleEnElConsultorio() {
        medicoActor.should(
                seeThat(PacienteVisibleQuestion.value(), is(false))
        );
    }

    @Then("el sistema muestra el mensaje {string}")
    public void elSistemaMuestraElMensaje(String expectedMessage) {
        medicoActor.should(
                seeThat(PausaProgramadaVisibleQuestion.value(), is(true)),
                seeThat(MensajePausaProgramadaQuestion.value(), equalTo(expectedMessage))
        );
    }

    @Then("el paciente continua visible en el consultorio")
    public void elPacienteContinuaVisibleEnElConsultorio() {
        medicoActor.should(
                seeThat(PacienteVisibleQuestion.value(), is(true))
        );
    }

    private void ensureStepLabel(String actualLabel, String expectedLabel, String context) {
        if (!expectedLabel.equals(actualLabel)) {
            throw new IllegalArgumentException(
                    "El paso declara " + context + "='" + actualLabel + "' pero debe ser '" + expectedLabel + "'");
        }
    }
}
