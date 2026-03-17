package steps;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Dimension;
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

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class SignUpSteps {

    private static final AtomicInteger WINDOW_SLOT = new AtomicInteger(0);

    @Managed
    private WebDriver driver;

    private Actor user;

    @Before
    public void setUp() {
        int slot = resolveWindowSlot();
        java.awt.Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        GraphicsDevice activeScreen = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();

        for (GraphicsDevice screen : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            Rectangle bounds = screen.getDefaultConfiguration().getBounds();
            if (bounds.contains(mouseLocation)) {
                activeScreen = screen;
                break;
            }
        }

        Rectangle screenBounds = activeScreen.getDefaultConfiguration().getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(activeScreen.getDefaultConfiguration());

        int availableX = screenBounds.x + insets.left;
        int availableY = screenBounds.y + insets.top;
        int availableWidth = screenBounds.width - insets.left - insets.right;
        int availableHeight = screenBounds.height - insets.top - insets.bottom;

        int halfWidth = availableWidth / 2;
        int xPosition = availableX + (slot == 0 ? 0 : halfWidth);

        driver.manage().window().setSize(new Dimension(halfWidth, availableHeight));
        driver.manage().window().setPosition(new org.openqa.selenium.Point(xPosition, availableY));

        user = ActorFactory.createActor("User", driver);
    }

    private int resolveWindowSlot() {
        String workerName = System.getProperty("org.gradle.test.worker");
        if (workerName != null && !workerName.isBlank()) {
            String workerNumber = workerName.replaceAll("\\D+", "");
            if (!workerNumber.isBlank()) {
                return Integer.parseInt(workerNumber) % 2;
            }
        }

        return WINDOW_SLOT.getAndIncrement() % 2;
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
