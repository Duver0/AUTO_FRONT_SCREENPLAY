package screenplay.actors;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

public class ActorFactory {

    private ActorFactory() {
    }

    public static Actor createActor(String name, WebDriver driver) {
        return Actor.named(name)
                .whoCan(BrowseTheWeb.with(driver));
    }
}
