package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Tasks;
import screenplay.utils.MedicalBackendClient;

public class RegistrarMedicoDePrueba implements Interaction {

    private final String name;
    private final String email;
    private final String password;

    public RegistrarMedicoDePrueba(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static Performable conDatos(String name, String email, String password) {
        return Tasks.instrumented(RegistrarMedicoDePrueba.class, name, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        MedicalBackendClient client = new MedicalBackendClient();
        client.registerMedico(name, email, password);
    }
}
