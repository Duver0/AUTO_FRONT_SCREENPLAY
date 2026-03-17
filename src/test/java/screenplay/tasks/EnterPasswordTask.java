package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import screenplay.ui.SignUpUI;

public class EnterPasswordTask implements Task {

    private final String password;

    public EnterPasswordTask(String password) {
        this.password = password;
    }

    public static Performable as(String password) {
        return Tasks.instrumented(EnterPasswordTask.class, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(password).into(SignUpUI.PASSWORD_INPUT));
    }
}
