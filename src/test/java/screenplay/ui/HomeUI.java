package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomeUI {

    public static final Target PAGE_HEADING =
            Target.the("main page heading").located(By.cssSelector("h1"));

    public static final Target NAVIGATION_MENU =
            Target.the("navigation menu").located(By.cssSelector("nav, [role='navigation'], header nav"));
}
