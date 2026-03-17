package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.GivenWhenThen;
import org.openqa.selenium.WebDriver;
import screenplay.actors.ActorFactory;
import screenplay.questions.FeedbackMessageQuestion;
import screenplay.questions.SignUpFormVisibleQuestion;
import screenplay.tasks.ClickRegisterButtonTask;
import screenplay.tasks.ClickRegisterLinkTask;
import screenplay.tasks.ClickSignInButtonTask;
import screenplay.tasks.EnterEmailTask;
import screenplay.tasks.EnterNameTask;
import screenplay.tasks.EnterPasswordTask;
import screenplay.tasks.OpenHomePageTask;

import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class SignUpSteps {

    @Managed
    private WebDriver driver;

    private Actor user;

    @Before
    public void setUp() {
        user = ActorFactory.createActor("User", driver);
    }

    @After
    public void tearDown() {
        BrowseTheWeb.as(user).getDriver().quit();
    }

    @Given("the user opens the application in the home page")
    public void theUserOpensTheApplicationInTheHomePage() {
        user.attemptsTo(OpenHomePageTask.as());
    }

    @When("the user clicks the sign in button")
    public void theUserClicksTheSignInButton() {
        user.attemptsTo(ClickSignInButtonTask.as());
    }

    @And("the user clicks the register link")
    public void theUserClicksTheRegisterLink() {
        user.attemptsTo(ClickRegisterLinkTask.as());
        user.should(GivenWhenThen.seeThat(SignUpFormVisibleQuestion.value(), is(true)));
    }

    @And("the user enters sign up data with name {string}, email {string} and password {string}")
    public void theUserEntersSignUpDataWithNameEmailAndPassword(String name, String email, String password) {
        String resolvedEmail = resolveDynamicEmail(email);
        user.attemptsTo(
                EnterNameTask.as(name),
            EnterEmailTask.as(resolvedEmail),
                EnterPasswordTask.as(password)
        );
    }

    @And("the user submits the sign up form")
    public void theUserSubmitsTheSignUpForm() {
        user.attemptsTo(ClickRegisterButtonTask.as());
    }

    @Then("the user should see the feedback message {string}")
    public void theUserShouldSeeTheFeedbackMessage(String expectedMessage) {
        user.should(GivenWhenThen.seeThat(FeedbackMessageQuestion.value(), containsString(expectedMessage)));
    }

    private String resolveDynamicEmail(String rawEmail) {
        if (!rawEmail.contains("<RANDOM_LONG>")) {
            return rawEmail;
        }

        long randomLong = ThreadLocalRandom.current().nextLong(100_000_000_000_000_000L, 900_000_000_000_000_000L);
        return rawEmail.replace("<RANDOM_LONG>", String.valueOf(randomLong));
    }
}
