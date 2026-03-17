package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import screenplay.ui.SignUpUI;

public class SignUpFormVisibleQuestion implements Question<Boolean> {

    public static Question<Boolean> value() {
        return new SignUpFormVisibleQuestion();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(SignUpUI.NAME_INPUT).answeredBy(actor)
                && Visibility.of(SignUpUI.EMAIL_INPUT).answeredBy(actor)
                && Visibility.of(SignUpUI.PASSWORD_INPUT).answeredBy(actor)
                && Visibility.of(SignUpUI.REGISTER_BUTTON).answeredBy(actor);
    }
}
