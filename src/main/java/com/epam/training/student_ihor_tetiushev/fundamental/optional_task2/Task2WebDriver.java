package com.epam.training.student_ihor_tetiushev.fundamental.optional_task2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task2WebDriver {
    public static void main(String[] args) throws InterruptedException {
        //WebDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        driver.get("https://pastebin.com/");
        WebElement pasteBtn = driver.findElement(By.className("header__btn"));
        pasteBtn.click();
        //pasteBtn.sendKeys(Keys.ESCAPE);

        WebElement textArea = driver.findElement(By.id("postform-text"));
        textArea.click();

        textArea.sendKeys("git config --global user.name  \"New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                "git push origin master --force\n");

        WebElement toggle = driver.findElement(By.id("paste-raw-on"));
        toggle.click();

        //textArea.sendKeys(Keys.ESCAPE);


        WebElement syntaxHighlightingField = driver.findElement(By.id("select2-postform-format-container"));
        syntaxHighlightingField.click();
        //syntaxHighlightingField.findElement(By.xpath("//li[contains(text(), 'Bash')]"));
        WebElement syntaxHighlightingFieldInput = driver.findElement(By.className("select2-search__field"));
        syntaxHighlightingFieldInput.sendKeys("Bash");
        syntaxHighlightingField.sendKeys(Keys.ENTER);


        //WebElement syntaxHighlightingField = driver.findElement(By.id("select2-search__field"));
        //syntaxHighlightingField.click();
        //syntaxHighlightingField.findElement(By.xpath("//option[contains(text(), 'Bash')]"));
        //syntaxHighlightingField.sendKeys("Bash");
        //syntaxHighlightingField.sendKeys(Keys.ENTER);











        WebElement expirationSelector = driver.findElement(By.id("select2-postform-expiration-container"));
        expirationSelector.click();

        WebElement option = expirationSelector.findElement(By.xpath("//li[contains(text(), '10 Minutes')]"));
        option.click();

        WebElement inputTitle = driver.findElement(By.id("postform-name"));
        inputTitle.sendKeys("helloweb");
        Thread.sleep(10000);
        driver.quit();


    }
}
