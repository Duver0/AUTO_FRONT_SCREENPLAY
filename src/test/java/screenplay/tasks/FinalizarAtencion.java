package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.ui.MedicalPanelUI;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FinalizarAtencion implements Task {

    public static Performable actual() {
        return Tasks.instrumented(FinalizarAtencion.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(MedicalPanelUI.FINALIZAR_ATENCION_BUTTON, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(MedicalPanelUI.FINALIZAR_ATENCION_BUTTON)
        );
    }
}
