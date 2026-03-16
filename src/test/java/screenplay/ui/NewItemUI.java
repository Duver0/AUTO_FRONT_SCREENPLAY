package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class NewItemUI {

    public static final Target CREATE_BUTTON =
            Target.the("create new item button").located(By.cssSelector("a[href*='new'], button.new-item, [data-testid='create-button']"));

    public static final Target TITLE_INPUT =
            Target.the("item title input field").located(By.cssSelector("input[name='title'], input[placeholder*='title' i], #title"));

    public static final Target SUBMIT_BUTTON =
            Target.the("form submit button").located(By.cssSelector("button[type='submit']"));

    public static final Target VALIDATION_ERROR =
            Target.the("validation error message").located(By.cssSelector("[role='alert']"));
}
