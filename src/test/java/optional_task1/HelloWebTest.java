package optional_task1;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

@Disabled

public class HelloWebTest {
    @Test
    void helloPastebinCom_newPaste() throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://pastebin.com/");
        WebElement pasteBtn = driver.findElement(By.className("header__btn"));
        pasteBtn.click();

        WebElement textArea = driver.findElement(By.id("postform-text"));
        textArea.click();

        textArea.sendKeys("Hello from WebDriver");

        WebElement expirationSelector = driver.findElement(By.id("select2-postform-expiration-container"));
        expirationSelector.click();

        WebElement option = expirationSelector.findElement(By.xpath("//li[contains(text(), '10 Minutes')]"));
        option.click();

        WebElement inputTitle = driver.findElement(By.id("postform-name"));
        inputTitle.sendKeys("helloweb");
        Thread.sleep(2000);
        driver.quit();
    }
}
