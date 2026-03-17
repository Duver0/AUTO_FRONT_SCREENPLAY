package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import screenplay.ui.SignInUI;

public class ClickRegisterLinkTask implements Task {

    public static Performable as() {
        return Tasks.instrumented(ClickRegisterLinkTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(SignInUI.REGISTER_LINK));
    }
}
