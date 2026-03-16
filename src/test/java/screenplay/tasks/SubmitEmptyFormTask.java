package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import screenplay.ui.NewItemUI;

public class SubmitEmptyFormTask implements Task {

    public static Performable withoutFillingRequiredFields() {
        return Tasks.instrumented(SubmitEmptyFormTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(NewItemUI.SUBMIT_BUTTON));
    }
}
