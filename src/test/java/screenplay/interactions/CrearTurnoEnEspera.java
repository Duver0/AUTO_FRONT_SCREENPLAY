package screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Tasks;
import screenplay.utils.MedicalBackendClient;

public class CrearTurnoEnEspera implements Interaction {

    private final String patientName;
    private final long patientDocument;

    public CrearTurnoEnEspera(String patientName, long patientDocument) {
        this.patientName = patientName;
        this.patientDocument = patientDocument;
    }

    public static Performable conDatos(String patientName, long patientDocument) {
        return Tasks.instrumented(CrearTurnoEnEspera.class, patientName, patientDocument);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        MedicalBackendClient client = new MedicalBackendClient();
        client.createTurno(patientName, patientDocument);
    }
}
