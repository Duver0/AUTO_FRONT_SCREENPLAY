package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import screenplay.ui.MedicalPanelUI;

public class PausaProgramadaVisibleQuestion implements Question<Boolean> {

    public static Question<Boolean> value() {
        return new PausaProgramadaVisibleQuestion();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(MedicalPanelUI.PAUSA_PROGRAMADA_HINT).answeredBy(actor);
    }
}
