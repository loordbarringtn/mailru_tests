package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

    public void typeLogin(WebDriver driver, String text) {
        driver.findElement(By.id("mailbox:login")).sendKeys(text);
    }

    public void clickSubmit(WebDriver driver) {
        driver.findElement(By.id("mailbox:submit")).click();
    }

    public void typePassword(WebDriver driver, String text) {
        driver.findElement(By.id("mailbox:password")).sendKeys(text);
    }
}
