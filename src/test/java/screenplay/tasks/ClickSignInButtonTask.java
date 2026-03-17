package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import screenplay.ui.HomeUI;

public class ClickSignInButtonTask implements Task {

    public static Performable as() {
        return Tasks.instrumented(ClickSignInButtonTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(HomeUI.SIGN_IN_BUTTON));
    }
}
