package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.HomeUI;

public class PageHeadingQuestion implements Question<String> {

    public static Question<String> value() {
        return new PageHeadingQuestion();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(HomeUI.PAGE_HEADING).viewedBy(actor).asString();
    }
}
