package final_task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FinalTaskTest {
    Logger log = Logger.getLogger(this.getClass().getName());


    static Stream<WebDriver> getWebDriverParams() {
        //provided headless mode
        /*ChromeOptions option1 = new ChromeOptions();
        option1.addArguments("headless");
        EdgeOptions option2= new EdgeOptions();
        option2.addArguments("headless");
        return Stream.of(new ChromeDriver(option1), new EdgeDriver(option2));*/
        return Stream.of(new ChromeDriver(), new EdgeDriver());
    }

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
        log.log(Level.INFO, "clearInputWithActions " + "target: "+ target);
        var actions = new Actions(driver);
        actions.doubleClick(target)
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();
    }

    @ParameterizedTest
    @MethodSource("getWebDriverParams")
    protected void useCase1(WebDriver driver) {
        log.log(Level.INFO, "Executing useCase1");
        driver.get("https://www.saucedemo.com/");


        log.log(Level.INFO, "Enter credentials (login and password)");
        WebElement loginPaste = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        loginPaste.sendKeys("User123");

        WebElement passwordPaste = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordPaste.sendKeys("passwordUser123");

        log.log(Level.INFO, "Clearing the inputs");
        clearInputWithActions(loginPaste, driver);
        clearInputWithActions(passwordPaste, driver);

        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        loginBtn.click();

        log.log(Level.INFO, "Checking the error window");
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//*[@id='login_button_container']/div/form/div[3]/h3")));

        WebElement errorMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3"));
        String errorText = errorMessage.getText();
        log.log(Level.INFO, "Checking the error message " + "error message: " + errorText);
        assertThat("Expected error message not displayed or username has already been entered!",
                errorText, containsString("Username is required"));

        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("getWebDriverParams")
    protected void useCase2(WebDriver driver) {
        log.log(Level.INFO, "Executing useCase 2");
        driver.get("https://www.saucedemo.com/");

        log.log(Level.INFO, "Enter credentials (login and password)");
        WebElement loginPaste = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        loginPaste.sendKeys("User001");

        WebElement passwordPaste = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        String strPasswordPaste = "admin";
        passwordPaste.sendKeys(strPasswordPaste);

        log.log(Level.INFO, "Clearing the inputs");
        clearControlText(strPasswordPaste, passwordPaste, driver);

        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        loginBtn.click();

        log.log(Level.INFO, "Checking the error window");
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//*[@id='login_button_container']/div/form/div[3]/h3")));

        WebElement errorMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3"));
        String errorText = errorMessage.getText();

        log.log(Level.INFO, "Checking the error message " + "error message: " + errorText);

        assertThat("Expected error message not displayed or password has already been entered!",
                errorText, containsString("Password is required"));

        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("getWebDriverParams")
    protected void useCase3(WebDriver driver) {
        log.log(Level.INFO, "Executing useCase 3");
        driver.get("https://www.saucedemo.com/");

        log.log(Level.INFO, "Enter credentials (login and password)");
        WebElement loginPaste = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        loginPaste.sendKeys("standard_user");

        WebElement passwordPaste = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordPaste.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        loginBtn.click();

        log.log(Level.INFO, "Checking the header container");
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//*[@id=\"header_container\"]/div[1]/div[2]/div")));

        WebElement title = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"));
        String titleText = title.getText();

        log.log(Level.INFO, "Checking the title into the header container " + "title text: " + titleText);
        assertThat("Title is not displayed or the title is not as expected!",
                titleText, containsString("Swag Labs"));

        driver.quit();
    }
}
