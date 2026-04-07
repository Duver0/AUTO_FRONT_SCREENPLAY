package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import screenplay.ui.SignUpUI;

public class SelectEmployeeUserTypeTask implements Task {

    public static Performable as() {
        return Tasks.instrumented(SelectEmployeeUserTypeTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SelectFromOptions.byValue("employee").from(SignUpUI.USER_TYPE_SELECT));
    }
}