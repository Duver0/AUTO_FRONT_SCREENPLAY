package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.SignUpUI;

public class FeedbackMessageQuestion implements Question<String> {

    public static Question<String> value() {
        return new FeedbackMessageQuestion();
    }

    @Override
    public String answeredBy(Actor actor) {
        String alertText = Text.of(SignUpUI.ALERT_FEEDBACK).answeredBy(actor);
        if (alertText != null && !alertText.isBlank()) {
            return alertText;
        }
        return Text.of(SignUpUI.STATUS_FEEDBACK).answeredBy(actor);
    }
}
