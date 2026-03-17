package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomeUI {

    public static final Target SIGN_IN_BUTTON =
            Target.the("sign in button in navbar")
                    .located(By.xpath("//a[normalize-space()='Iniciar sesión'] | //button[normalize-space()='Iniciar sesión']"));
}
