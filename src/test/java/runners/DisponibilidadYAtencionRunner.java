package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/disponibilidad_y_atencion.feature",
        glue = "steps",
        plugin = {"pretty"},
        tags = "@disponibilidad_y_atencion"
)
public class DisponibilidadYAtencionRunner {
}
