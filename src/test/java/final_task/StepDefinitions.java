package final_task;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StepDefinitions {
    WebDriver driver = new EdgeDriver();
    private static final Logger log = Logger.getLogger(StepDefinitions.class.getName());

    private void clearControlText(String text, WebElement element, WebDriver driver) {
        log.log(Level.INFO, "clearControlText " + "element: " + element + "element length: " + text.length());
        Actions actions = new Actions(driver);
        for (int i = 0; i < text.length(); i++) {
            actions.click(element)
                    .sendKeys(Keys.BACK_SPACE)
                    .build()
                    .perform();
        }
    }

    private void clearInputWithActions(WebElement target, WebDriver driver) {
        log.log(Level.INFO, "clearInputWithActions " + "target: " + target);
        var actions = new Actions(driver);
        actions.doubleClick(target)
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();
    }
    @Given("I am on the SauceDemo login page")
    public void openLoginPage() {
        log.log(Level.INFO, "Opening SauceDemo login page");
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter login {string} and password {string}")
    public void enterCredentials(String login, String password) {
        log.log(Level.INFO, "Entering credentials");
        WebElement loginPaste = driver.findElement(By.xpath("//*[@id='user-name']"));
        loginPaste.sendKeys(login);

        WebElement passwordPaste = driver.findElement(By.xpath("//*[@id='password']"));
        passwordPaste.sendKeys(password);
    }

    @When("I clear the username and password fields")
    public void clearInputFields() {
        log.log(Level.INFO, "Clearing input fields");
        WebElement loginPaste = driver.findElement(By.xpath("//*[@id='user-name']"));
        WebElement passwordPaste = driver.findElement(By.xpath("//*[@id='password']"));

        clearInputWithActions(loginPaste,driver);
        clearInputWithActions(passwordPaste,driver);
    }

    @When("I click the login button")
    public void clickLoginButton() {
        log.log(Level.INFO, "Clicking login button");
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id='login-button']"));
        loginBtn.click();
    }

    @Then("I should see an error message containing {string}")
    public void checkErrorMessage(String expectedMessage) {
        log.log(Level.INFO, "Checking the error message");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3")));

        WebElement errorMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3"));
        String actualMessage = errorMessage.getText();
        log.log(Level.INFO, "Error message: " + actualMessage);
        Assertions.assertTrue(actualMessage.contains(expectedMessage),
                "Expected error message not displayed!");
        driver.close();
        driver.quit();
    }
}
