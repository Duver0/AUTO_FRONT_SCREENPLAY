package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.ui.MedicalPanelUI;
import screenplay.ui.MedicalSignInUI;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarSesionComoMedico implements Task {

    private final String email;
    private final String password;

    public IniciarSesionComoMedico(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Performable conCredenciales(String email, String password) {
        return Tasks.instrumented(IniciarSesionComoMedico.class, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OpenHomePageTask.as(),
                ClickSignInButtonTask.as(),
                WaitUntil.the(MedicalSignInUI.EMAIL_INPUT, isVisible()).forNoMoreThan(20).seconds(),
                Enter.theValue(email).into(MedicalSignInUI.EMAIL_INPUT),
                Enter.theValue(password).into(MedicalSignInUI.PASSWORD_INPUT),
                Click.on(MedicalSignInUI.SUBMIT_BUTTON),
                WaitUntil.the(MedicalPanelUI.PANEL_TITLE, isVisible()).forNoMoreThan(25).seconds()
        );
    }
}
