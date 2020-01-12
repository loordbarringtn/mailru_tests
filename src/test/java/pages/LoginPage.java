package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.TestBase;

public class LoginPage {

    public void typeLogin(WebDriver driver, String login) {
        driver.findElement(By.id("mailbox:login")).sendKeys(login);
    }

    public void clickSubmit(WebDriver driver) {
        driver.findElement(By.id("mailbox:submit")).click();
    }

    public void typePassword(WebDriver driver, String password) {
        driver.findElement(By.id("mailbox:password")).sendKeys(password);
    }

}
