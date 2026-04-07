package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.MedicalPanelUI;

public class MensajePausaProgramadaQuestion implements Question<String> {

    public static Question<String> value() {
        return new MensajePausaProgramadaQuestion();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(MedicalPanelUI.PAUSA_PROGRAMADA_HINT).answeredBy(actor).trim();
    }
}
