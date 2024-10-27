package com.epam.training.student_ihor_tetiushev.fundamental.optional_task1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Array;

public class HelloWeb {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://pastebin.com/");
        /*WebElement pasteBtn = driver.findElement(By.className("header__btn"));
        pasteBtn.click();
        pasteBtn.sendKeys(Keys.ESCAPE);*/

        WebElement textArea = driver.findElement(By.id("postform-text"));
        textArea.click();

        textArea.sendKeys("Hello from WebDriver");
        //textArea.sendKeys(Keys.ESCAPE);

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
