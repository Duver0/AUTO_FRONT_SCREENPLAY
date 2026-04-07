package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.MedicalPanelUI;

public class EstadoConsultorioQuestion implements Question<String> {

    public static Question<String> value() {
        return new EstadoConsultorioQuestion();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(MedicalPanelUI.ESTADO_CONSULTORIO).answeredBy(actor).trim();
    }
}
