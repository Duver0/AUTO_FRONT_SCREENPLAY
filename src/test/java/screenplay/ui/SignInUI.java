package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SignInUI {

    public static final Target REGISTER_LINK =
            Target.the("register link in sign in form")
                    .located(By.xpath("//a[normalize-space()='Regístrate'] | //button[normalize-space()='Regístrate']"));
}
