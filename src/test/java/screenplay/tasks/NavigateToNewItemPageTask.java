package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import screenplay.ui.NewItemUI;

public class NavigateToNewItemPageTask implements Task {

    public static Performable fromTheHomePage() {
        return Tasks.instrumented(NavigateToNewItemPageTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(NewItemUI.CREATE_BUTTON));
    }
}
