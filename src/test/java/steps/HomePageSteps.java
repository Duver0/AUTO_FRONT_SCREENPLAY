package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import screenplay.actors.ActorFactory;
import screenplay.questions.PageHeadingQuestion;
import screenplay.tasks.OpenHomePageTask;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class HomePageSteps {

    @Managed
    private WebDriver driver;

    private Actor visitor;

    @Before
    public void setUp() {
        visitor = ActorFactory.createActor("Visitor", driver);
    }

    @Given("a visitor accesses the application")
    public void aVisitorAccessesTheApplication() {
        visitor.attemptsTo(OpenHomePageTask.toTheHomePage());
    }

    @Then("the home page heading should be visible")
    public void theHomePageHeadingShouldBeVisible() {
        visitor.should(GivenWhenThen.seeThat(PageHeadingQuestion.value(), not(emptyOrNullString())));
    }
}
