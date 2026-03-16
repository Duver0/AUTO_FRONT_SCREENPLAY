package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import screenplay.actors.ActorFactory;
import screenplay.questions.ValidationErrorQuestion;
import screenplay.tasks.NavigateToNewItemPageTask;
import screenplay.tasks.OpenHomePageTask;
import screenplay.tasks.SubmitEmptyFormTask;

import static org.hamcrest.Matchers.is;

public class NewItemSteps {

    @Managed
    private WebDriver driver;

    private Actor user;

    @Before
    public void setUp() {
        user = ActorFactory.createActor("User", driver);
    }

    @Given("a user is on the new item creation page")
    public void aUserIsOnTheNewItemCreationPage() {
        user.attemptsTo(
                OpenHomePageTask.toTheHomePage(),
                NavigateToNewItemPageTask.fromTheHomePage()
        );
    }

    @When("the user submits the form without providing a title")
    public void theUserSubmitsTheFormWithoutProvidingATitle() {
        user.attemptsTo(SubmitEmptyFormTask.withoutFillingRequiredFields());
    }

    @Then("a required field validation error should be displayed")
    public void aRequiredFieldValidationErrorShouldBeDisplayed() {
        user.should(GivenWhenThen.seeThat(ValidationErrorQuestion.isDisplayed(), is(true)));
    }
}
