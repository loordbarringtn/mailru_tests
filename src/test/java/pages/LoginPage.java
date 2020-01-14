package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    public void verifyLoadingHidden(WebDriver driver) {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.id("octopus-loader")));
    }

    public void waitForPasswordVisibility (WebDriver driver) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("mailbox:password")));
    }

}
