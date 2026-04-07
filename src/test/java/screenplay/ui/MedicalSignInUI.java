package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class MedicalSignInUI {

    public static final Target EMAIL_INPUT =
            Target.the("email input in sign in form")
                    .located(By.cssSelector("input[placeholder='Email']"));

    public static final Target PASSWORD_INPUT =
            Target.the("password input in sign in form")
                    .located(By.cssSelector("input[type='password']"));

    public static final Target SUBMIT_BUTTON =
            Target.the("submit button in sign in form")
                    .located(By.cssSelector("button[type='submit']"));
}
