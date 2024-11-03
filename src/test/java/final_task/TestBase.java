package final_task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class TestBase {
    private final WebDriver driver;

    TestBase(WebDriver driver) {
        this.driver = driver;
    }

    private void clearControlText(String text, WebElement element) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < text.length(); i++) {
            actions.click(element)
                    .sendKeys(Keys.BACK_SPACE)
                    .build()
                    .perform();
        }
    }


    private void clearInputWithActions(WebElement target) {
        var actions = new Actions(driver);
        actions.doubleClick(target)
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();
    }

    @Test
    protected void useCase1(){
        driver.get("https://www.saucedemo.com/");

        WebElement loginPaste = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        loginPaste.sendKeys("User123");

        WebElement passwordPaste = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordPaste.sendKeys("passwordUser123");

        clearInputWithActions(loginPaste);
        clearInputWithActions(passwordPaste);


        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        loginBtn.click();

        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[@id='login_button_container']" +
                        "/div/form/div[3]/h3[contains(text(), 'Username is required')]")
                ));
        driver.quit();
    }


    @Test
    protected void useCase2(){
        driver.get("https://www.saucedemo.com/");

        WebElement loginPaste = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        loginPaste.sendKeys("User001");

        WebElement passwordPaste = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        String strPasswordPaste = "admin";
        passwordPaste.sendKeys(strPasswordPaste);

        clearControlText(strPasswordPaste, passwordPaste);


        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        loginBtn.click();

        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[@id=\"login_button_container\"]" +
                        "/div/form/div[3]/h3[contains(text(), 'Password is required')]")));
        driver.quit();
    }
}
