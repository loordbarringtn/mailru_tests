package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static helpers.EnvironmentHelper.firstEmailLogin;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MailRuTests extends TestBase {

    @Test
    @DisplayName("Positive test")
    void positiveTest() {
        driver.get(baseUrl);
        driver2.get(baseUrl);
        driver.findElement(By.id("mailbox:login")).sendKeys(firstEmailLogin);
        driver.findElement(By.id("mailbox:submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mailbox:password")));
        driver.findElement(By.id("mailbox:password")).sendKeys("qw_23456_mjko");
        driver.findElement(By.id("mailbox:submit")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("octopus-loader")));

        String thisEmail = driver.findElement(By.id("PH_user-email")).getText();
        assertEquals(thisEmail, firstEmailLogin, "should be logged in");

    }
}
