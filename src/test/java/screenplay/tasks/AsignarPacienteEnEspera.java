package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.interactions.CrearTurnoEnEspera;
import screenplay.ui.MedicalPanelUI;
import screenplay.utils.TestMedicalDataFactory;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AsignarPacienteEnEspera implements Task {

    public static Performable enElConsultorioActual() {
        return Tasks.instrumented(AsignarPacienteEnEspera.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String patientName = TestMedicalDataFactory.uniqueName("paciente");
        long patientDocument = TestMedicalDataFactory.uniqueDocument();

        actor.attemptsTo(
                CrearTurnoEnEspera.conDatos(patientName, patientDocument),
                WaitUntil.the(MedicalPanelUI.INICIAR_ATENCION_BUTTON, isVisible()).forNoMoreThan(50).seconds(),
                Click.on(MedicalPanelUI.INICIAR_ATENCION_BUTTON),
                WaitUntil.the(MedicalPanelUI.ESTADO_CONSULTORIO, containsText("En atencion")).forNoMoreThan(40).seconds(),
                WaitUntil.the(MedicalPanelUI.ESTADO_ATENCION_PACIENTE, containsText("Atencion en curso")).forNoMoreThan(25).seconds()
        );
    }
}
