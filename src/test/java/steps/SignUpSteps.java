package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.LocalDateTime;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import screenplay.questions.FeedbackMessageQuestion;
import screenplay.questions.SignUpFormVisibleQuestion;
import screenplay.tasks.ClickRegisterButtonTask;
import screenplay.tasks.ClickRegisterLinkTask;
import screenplay.tasks.ClickSignInButtonTask;
import screenplay.tasks.FillSignUpFormTask;
import screenplay.tasks.OpenHomePageTask;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class SignUpSteps {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("the user opens the application in the home page")
    public void theUserOpensTheApplicationInTheHomePage() {
        theActorCalled("User").attemptsTo(OpenHomePageTask.as());
    }

    @When("the user clicks the sign in button")
    public void theUserClicksTheSignInButton() {
        theActorInTheSpotlight().attemptsTo(ClickSignInButtonTask.as());
    }

    @And("the user clicks the register link")
    public void theUserClicksTheRegisterLink() {
        theActorInTheSpotlight().attemptsTo(ClickRegisterLinkTask.as());
    }

    @And("the user enters sign up data with name {string}, email {string} and password {string}")
    public void theUserEntersSignUpDataWithNameEmailAndPassword(String name, String email, String password) {
        String resolvedEmail = resolveDynamicEmail(email);
        theActorInTheSpotlight().attemptsTo(
                FillSignUpFormTask.withData(name, resolvedEmail, password)
        );
    }

    @And("the user submits the sign up form")
    public void theUserSubmitsTheSignUpForm() {
        theActorInTheSpotlight().attemptsTo(ClickRegisterButtonTask.as());
    }

    @Then("the user should see the feedback message {string}")
    public void theUserShouldSeeTheFeedbackMessage(String expectedMessage) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(FeedbackMessageQuestion.value(), containsString(expectedMessage)));
    }

    private String resolveDynamicEmail(String rawEmail) {
        if (!rawEmail.contains("<DATE_DIGIT_SUM>")) {
            return rawEmail;
        }

        LocalDateTime now = LocalDateTime.now();
        int digitsSum = now.getDayOfMonth()
                + now.getMonthValue()
                + (now.getYear() % 100)
                + now.getHour()
                + now.getMinute()
                + now.getSecond();

        return rawEmail.replace("<DATE_DIGIT_SUM>", String.valueOf(digitsSum));
    }
}
