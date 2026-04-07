package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.ui.MedicalPanelUI;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class VincularseAConsultorio implements Task {

    public static Performable alPrimerConsultorioDisponible() {
        return Tasks.instrumented(VincularseAConsultorio.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(MedicalPanelUI.CONSULTORIO_SELECTOR, isVisible()).forNoMoreThan(20).seconds(),
                SelectFromOptions.byIndex(0).from(MedicalPanelUI.CONSULTORIO_SELECTOR),
                Click.on(MedicalPanelUI.TOMAR_CONSULTORIO_BUTTON),
                WaitUntil.the(MedicalPanelUI.ESTADO_CONSULTORIO, containsText("Disponible")).forNoMoreThan(40).seconds()
        );
    }
}
