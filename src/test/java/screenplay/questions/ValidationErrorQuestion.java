package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.NewItemUI;

public class ValidationErrorQuestion implements Question<Boolean> {

    public static Question<Boolean> isDisplayed() {
        return new ValidationErrorQuestion();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String errorText = Text.of(NewItemUI.VALIDATION_ERROR).viewedBy(actor).asString();
        return !errorText.isEmpty();
    }
}
