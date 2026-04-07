package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.ui.MedicalPanelUI;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class MarcarNoDisponible implements Task {

    public static Performable enAtencionActiva() {
        return Tasks.instrumented(MarcarNoDisponible.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(MedicalPanelUI.PAUSAR_ATENCION_BUTTON, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(MedicalPanelUI.PAUSAR_ATENCION_BUTTON),
                WaitUntil.the(MedicalPanelUI.PAUSA_PROGRAMADA_HINT, isVisible()).forNoMoreThan(30).seconds()
        );
    }
}
