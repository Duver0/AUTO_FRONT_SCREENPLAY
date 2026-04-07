package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.MedicalPanelUI;

public class EstadoAtencionPacienteQuestion implements Question<String> {

    public static Question<String> value() {
        return new EstadoAtencionPacienteQuestion();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(MedicalPanelUI.ESTADO_ATENCION_PACIENTE).answeredBy(actor).trim();
    }
}
