package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SignUpUI {

    public static final Target NAME_INPUT =
            Target.the("name input")
                    .located(By.cssSelector("input[placeholder='Nombre']"));

    public static final Target EMAIL_INPUT =
            Target.the("email input")
                    .located(By.cssSelector("input[placeholder='Email']"));

    public static final Target USER_TYPE_SELECT =
            Target.the("user type select")
                    .located(By.cssSelector("select[aria-label='Tipo de usuario']"));

    public static final Target PASSWORD_INPUT =
            Target.the("password input")
                    .located(By.cssSelector("input[placeholder='Contraseña']"));

    public static final Target REGISTER_BUTTON =
            Target.the("register submit button")
                    .located(By.xpath("//button[normalize-space()='Registrarse' or normalize-space()='Registrando...']"));

    public static final Target ALERT_FEEDBACK =
            Target.the("alert feedback")
                    .located(By.cssSelector("[role='alert']"));

    public static final Target STATUS_FEEDBACK =
            Target.the("status feedback")
                    .located(By.cssSelector("[role='status']"));
}
