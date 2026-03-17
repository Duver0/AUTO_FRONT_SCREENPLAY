package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import screenplay.ui.SignUpUI;

public class EnterEmailTask implements Task {

    private final String email;

    public EnterEmailTask(String email) {
        this.email = email;
    }

    public static Performable as(String email) {
        return Tasks.instrumented(EnterEmailTask.class, email);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(email).into(SignUpUI.EMAIL_INPUT));
    }
}
