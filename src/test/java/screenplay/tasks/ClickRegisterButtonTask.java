package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import screenplay.ui.SignUpUI;

public class ClickRegisterButtonTask implements Task {

    public static Performable as() {
        return Tasks.instrumented(ClickRegisterButtonTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(SignUpUI.REGISTER_BUTTON));
    }
}
