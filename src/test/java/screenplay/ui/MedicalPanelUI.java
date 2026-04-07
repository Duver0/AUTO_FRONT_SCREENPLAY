package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class MedicalPanelUI {

    public static final Target PANEL_TITLE =
            Target.the("medical panel title")
                    .located(By.xpath("//h1[normalize-space()='Panel de consultorio']"));

    public static final Target CONSULTORIO_SELECTOR =
            Target.the("consultorio selector")
                    .located(By.id("consultorio-selector"));

    public static final Target TOMAR_CONSULTORIO_BUTTON =
            Target.the("tomar consultorio button")
                    .located(By.xpath("//button[normalize-space()='Tomar consultorio']"));

    public static final Target INICIAR_ATENCION_BUTTON =
            Target.the("iniciar atencion button")
                    .located(By.xpath("//button[normalize-space()='Iniciar atencion']"));

    public static final Target FINALIZAR_ATENCION_BUTTON =
            Target.the("finalizar atencion button")
                    .located(By.xpath("//button[normalize-space()='Finalizar atencion']"));

    public static final Target PAUSAR_ATENCION_BUTTON =
            Target.the("pausar atencion button")
                    .located(By.xpath("//button[normalize-space()='Pausar atencion']"));

    public static final Target ESTADO_CONSULTORIO =
            Target.the("estado del consultorio value")
                    .located(By.xpath("//p[normalize-space()='Estado del consultorio']/following-sibling::h2"));

    public static final Target NOMBRE_PACIENTE =
            Target.the("nombre del paciente actual")
                    .located(By.xpath("//span[normalize-space()='Nombre']/following-sibling::p"));

    public static final Target ESTADO_ATENCION_PACIENTE =
            Target.the("estado de atencion del paciente")
                    .located(By.xpath("//span[normalize-space()='Estado de atencion']/following-sibling::p"));

    public static final Target PAUSA_PROGRAMADA_HINT =
            Target.the("pausa programada hint")
                    .located(By.xpath("//p[contains(normalize-space(),'Pausa programada: al finalizar esta atencion no se asignaran mas pacientes.')]"));
}
