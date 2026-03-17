package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import screenplay.ui.SignUpUI;

public class EnterNameTask implements Task {

    private final String name;

    public EnterNameTask(String name) {
        this.name = name;
    }

    public static Performable as(String name) {
        return Tasks.instrumented(EnterNameTask.class, name);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(name).into(SignUpUI.NAME_INPUT));
    }
}
