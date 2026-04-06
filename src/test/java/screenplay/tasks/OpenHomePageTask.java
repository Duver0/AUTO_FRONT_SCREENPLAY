package screenplay.tasks;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.File;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class OpenHomePageTask implements Task {

    private static final String SERENITY_CONFIG_FILE = "serenity.conf";
    private static final String BASE_URL_KEY = "webdriver.base.url";

    public static Performable as() {
        return Tasks.instrumented(OpenHomePageTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(baseUrlFromSerenityConfig()));
    }

    private String baseUrlFromSerenityConfig() {
        Config config = ConfigFactory.parseFile(new File(SERENITY_CONFIG_FILE)).resolve();

        if (!config.hasPath(BASE_URL_KEY)) {
            throw new IllegalStateException("webdriver.base.url is not configured in serenity.conf");
        }

        String baseUrl = config.getString(BASE_URL_KEY);
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("webdriver.base.url is empty in serenity.conf");
        }

        return baseUrl;
    }
}
