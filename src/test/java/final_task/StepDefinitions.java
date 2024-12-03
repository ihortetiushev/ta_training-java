package final_task;

import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StepDefinitions {
    private static final WebDriver driver = getWebDriver("driver1");
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
    //For UC-1
    @Given("I am on the SauceDemo login page")
    public void openLoginPage() {
        log.log(Level.INFO, "Opening SauceDemo login page");
        driver.manage().window().maximize();
        driver.get(FinalTaskTest.TESTED_URL);
    }

    @When("I enter login {string} and password {string}")
    public void enterCredentials(String loginProperty, String passwordProperty) {
        log.log(Level.INFO, "Entering credentials");
        String login = PropertyReader.getProperties(loginProperty, null);
        String password = PropertyReader.getProperties(passwordProperty, null);

        WebElement loginPaste = driver.findElement(By.xpath(FinalTaskTest.LOGIN_PASTE_XPATH));
        loginPaste.sendKeys(login);
        WebElement passwordPaste = driver.findElement(By.xpath(FinalTaskTest.PASSWORD_PASTE_XPATH));
        passwordPaste.sendKeys(password);
    }

    @When("I click the login button")
    public void clickLoginButton() {
        log.log(Level.INFO, "Clicking login button");
        WebElement loginBtn = driver.findElement(By.xpath(FinalTaskTest.LOGIN_BUTTON_XPATH));
        loginBtn.click();
    }

    @Then("I should see an error message containing {string}")
    public void checkErrorMessage(String expectedMessage) {
        log.log(Level.INFO, "Checking the error message");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='error-message-container error']//h3")));

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']//h3"));
        String actualMessage = errorMessage.getText();
        log.log(Level.INFO, "Error message: " + actualMessage);
        Assertions.assertTrue(actualMessage.contains(expectedMessage),
                "Expected error message not displayed! Expected error: " + expectedMessage + ". Actual error message: " + actualMessage);
    }

    //For UC-2
    @When("I clear the {string} field")
    public void iClearOneField(String field) {
        if ("login".equalsIgnoreCase(field)) {
            WebElement loginField = driver.findElement(By.xpath(FinalTaskTest.LOGIN_PASTE_XPATH));
            String loginStr = loginField.getText();

            clearInputWithActions(loginField, driver);
            clearControlText(loginStr, loginField, driver);
        } else if ("password".equalsIgnoreCase(field)) {
            WebElement passwordField = driver.findElement(By.xpath(FinalTaskTest.PASSWORD_PASTE_XPATH));
            String passwordStr = passwordField.getText();
            clearInputWithActions(passwordField, driver);
            clearControlText(passwordStr, passwordField, driver);
        }
    }
    //For UC-3
    @Then("I should see validation the title {string} in the dashboard")
    public void checkTitle(String expectedTitle) {
        log.log(Level.INFO, "Checking the expected title");

        WebElement title = driver.findElement(By.xpath(FinalTaskTest.TITLE_XPATH));
        String actualTitle = title.getText();

        log.log(Level.INFO, "Title: " + actualTitle);
        Assertions.assertTrue(actualTitle.contains(expectedTitle),
                "The title is different or it is not displayed!");
    }
    @AfterAll
    public static void closingWebBrowsers() {
        driver.quit();
    }

    @SuppressWarnings("unchecked")
    private static WebDriver getWebDriver(String key) {
        Class<WebDriver> clazz = null;
        try {
            clazz = (Class<WebDriver>) Class.forName(PropertyReader.getProperties(key, "org.openqa.selenium.chrome.ChromeDriver"));
            return clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
