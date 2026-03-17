package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import screenplay.ui.SignUpUI;

public class FillSignUpFormTask implements Task {

    private final String name;
    private final String email;
    private final String password;

    public FillSignUpFormTask(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static Performable withData(String name, String email, String password) {
        return Tasks.instrumented(FillSignUpFormTask.class, name, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(name).into(SignUpUI.NAME_INPUT),
                Enter.theValue(email).into(SignUpUI.EMAIL_INPUT),
                Enter.theValue(password).into(SignUpUI.PASSWORD_INPUT)
        );
    }
}
