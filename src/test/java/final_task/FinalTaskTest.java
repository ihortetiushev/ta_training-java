package final_task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FinalTaskTest {
    protected static final String TESTED_URL = PropertyReader.getProperties("testedUrl", null);
    protected static final String LOGIN_PASTE_XPATH = PropertyReader.getProperties("loginPasteXPath", null);
    protected static final String PASSWORD_PASTE_XPATH = PropertyReader.getProperties("passwordPasteXPath", null);
    protected static final String LOGIN_BUTTON_XPATH = PropertyReader.getProperties("loginButtonXPath", null);
    protected static final String TITLE_XPATH = PropertyReader.getProperties("titleXPath", null);
    private static final Logger log = Logger.getLogger(FinalTaskTest.class.getName());
    private static final boolean HEADLESS_MODE = Boolean.parseBoolean(PropertyReader.getProperties("headlessMode", "false"));
    static final private List<WebDriver> DRIVERS = new ArrayList<>();

    static Stream<WebDriver> getWebDriverParams() {
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions = new EdgeOptions();
        if (HEADLESS_MODE) {
            chromeOptions.addArguments("headless");
            edgeOptions.addArguments("headless");
        }
        return Stream.of(new ChromeDriver(chromeOptions), new EdgeDriver(edgeOptions));
    }

    private static void addDriverIfAbsent(WebDriver driver) {
        if (!DRIVERS.contains(driver)) {
            DRIVERS.add(driver);
            driver.manage().window().maximize();

        }
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
        log.log(Level.INFO, "clearInputWithActions " + "target: " + target);
        var actions = new Actions(driver);
        actions.doubleClick(target)
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();
    }

    @ParameterizedTest
    @MethodSource("getWebDriverParams")
    protected void useCase1(WebDriver driver) {

        addDriverIfAbsent(driver);
        log.log(Level.INFO, "Executing useCase1");
        driver.get(TESTED_URL);

        log.log(Level.INFO, "Enter credentials (login and password)");
        WebElement loginPaste = driver.findElement(By.xpath(LOGIN_PASTE_XPATH));
        loginPaste.sendKeys("User123");

        WebElement passwordPaste = driver.findElement(By.xpath(PASSWORD_PASTE_XPATH));
        passwordPaste.sendKeys("passwordUser123");

        log.log(Level.INFO, "Clearing the inputs");
        clearInputWithActions(loginPaste, driver);
        clearInputWithActions(passwordPaste, driver);

        WebElement loginBtn = driver.findElement(By.xpath(LOGIN_BUTTON_XPATH));
        loginBtn.click();

        log.log(Level.INFO, "Checking the error window");
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//div[@class='error-message-container error']" +
                                "//h3[text()='Epic sadface: Username is required']")));

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']" +
                "//h3[text()='Epic sadface: Username is required']"));
        String errorText = errorMessage.getText();
        log.log(Level.INFO, "Checking the error message " + "error message: " + errorText);
        assertThat("Expected error message not displayed or username has already been entered!",
                errorText, equalTo("Epic sadface: Username is required"));
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("getWebDriverParams")
    protected void useCase2(WebDriver driver) {
        addDriverIfAbsent(driver);
        log.log(Level.INFO, "Executing useCase 2");
        driver.get(TESTED_URL);

        log.log(Level.INFO, "Enter credentials (login and password)");
        WebElement loginPaste = driver.findElement(By.xpath(LOGIN_PASTE_XPATH));
        loginPaste.sendKeys("User001");

        WebElement passwordPaste = driver.findElement(By.xpath(PASSWORD_PASTE_XPATH));
        String strPasswordPaste = "admin";
        passwordPaste.sendKeys(strPasswordPaste);

        log.log(Level.INFO, "Clearing the inputs");
        clearControlText(strPasswordPaste, passwordPaste, driver);

        WebElement loginBtn = driver.findElement(By.xpath(LOGIN_BUTTON_XPATH));
        loginBtn.click();

        log.log(Level.INFO, "Checking the error window");
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//div[@class='error-message-container error']" +
                                "//h3[text()='Epic sadface: Password is required']")));

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']" +
                "//h3[text()='Epic sadface: Password is required']"));
        String errorText = errorMessage.getText();

        log.log(Level.INFO, "Checking the error message " + "error message: " + errorText);

        assertThat("Expected error message not displayed or password has already been entered!",
                errorText, equalTo("Epic sadface: Password is required"));
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("getWebDriverParams")
    protected void useCase3(WebDriver driver) {
        addDriverIfAbsent(driver);
        log.log(Level.INFO, "Executing useCase 3");
        driver.get(TESTED_URL);

        log.log(Level.INFO, "Enter credentials (login and password)");
        WebElement loginPaste = driver.findElement(By.xpath(LOGIN_PASTE_XPATH));
        loginPaste.sendKeys("standard_user");

        WebElement passwordPaste = driver.findElement(By.xpath(PASSWORD_PASTE_XPATH));
        passwordPaste.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.xpath(LOGIN_BUTTON_XPATH));
        loginBtn.click();

        log.log(Level.INFO, "Checking the header container");
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(TITLE_XPATH)));
        WebElement title = driver.findElement(By.xpath(TITLE_XPATH));
        String titleText = title.getText();

        log.log(Level.INFO, "Checking the title into the header container " + "title text: " + titleText);
        assertThat("Title is not displayed or the title is not as expected!",
                titleText, equalTo("Swag Labs"));
        driver.quit();
    }
    /*@ParameterizedTest
    @MethodSource("getWebDriverParams")
    @AfterAll
    public static void afterAll() {
        drivers.forEach(WebDriver::quit);
    }*/
}
