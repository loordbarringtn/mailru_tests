package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.SentLettersPage;

import static helpers.EnvironmentHelper.*;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.RandomUtils.getRandomString;

@Tag("mailtests")
public class MailRuTests extends TestBase  {
    @Test
    public void authorizationTest() {
        LoginPage loginPage = new LoginPage();
        driver.get(baseUrl);

        loginPage.typeLogin(driver, firstEmailLogin);
        loginPage.clickSubmit(driver);
        loginPage.typePassword(driver,firstEmailPassword);
        loginPage.clickSubmit(driver);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("octopus-loader")));
        String thisEmail = driver.findElement(By.id("PH_user-email")).getText();
        assertEquals(thisEmail, firstEmailLogin, "should be logged in");
    }

    @Test
    public void checkEmailSent() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        SentLettersPage sentLettersPage = new SentLettersPage();
        String subject = getRandomString(15);
        String emailText = getRandomString(50);
        driver.get(baseUrl);

        loginPage.typeLogin(driver, firstEmailLogin);
        loginPage.clickSubmit(driver);
        loginPage.typePassword(driver,firstEmailPassword);
        loginPage.clickSubmit(driver);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("octopus-loader")));
        sentLettersPage.composeButtonClick(driver);
        sentLettersPage.recepientAddressFill(driver,secondEmailLogin);
        sentLettersPage.subjectFill(driver,subject);
        sentLettersPage.textFieldFill(driver,emailText);

        driver.findElement(By.xpath("//span[text()='Отправить']")).click();
        driver.findElement(By.xpath("//span[@title='Закрыть']")).click();
        driver.findElement(By.xpath("//a[@href='/sent/']")).click();

        WebElement element = driver.findElement(By.cssSelector(".letter-list"))
                .findElement(By.xpath("//span[text()='" + subject + "']"));
        assertTrue(element.isDisplayed(), "letter with subject " + subject + "should be in sent letters list");

    }

    @Test
    public void sendEmailTest() throws InterruptedException { // todo refactor authorization to api+cookie
        String subject = getRandomString(15);
        driver.get(baseUrl);

        driver.findElement(By.id("mailbox:login")).sendKeys(firstEmailLogin);
        driver.findElement(By.id("mailbox:submit")).click();
        driver.findElement(By.id("mailbox:password")).sendKeys(firstEmailPassword);
        driver.findElement(By.id("mailbox:submit")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("octopus-loader")));

        String thisEmail = driver.findElement(By.id("PH_user-email")).getText();
        assertEquals(thisEmail, firstEmailLogin, "should be logged in");

        // send email // todo refactor send message by api
        driver.findElement(By.xpath("//span[@class='compose-button__txt']")).click();
        driver.findElement(By.xpath("//div[@data-type='to']//input")).sendKeys(secondEmailLogin);
        driver.findElement(By.xpath("//input[@name='Subject']")).sendKeys(subject);
        driver.findElement(By.xpath("//div[@class='cke_widget_editable']/div")).sendKeys("Привет");
        driver.findElement(By.xpath("//span[text()='Отправить']")).click();

        startSecondDriver();
        driver2.get(baseUrl);
        driver2.findElement(By.id("mailbox:login")).sendKeys(secondEmailLogin);
        driver2.findElement(By.id("mailbox:submit")).click();
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("mailbox:password")));
        driver2.findElement(By.id("mailbox:password")).sendKeys(secondEmailPassword);
        driver2.findElement(By.id("mailbox:submit")).click();
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("octopus-loader")));

        String thisSecondEmail = driver2.findElement(By.id("PH_user-email")).getText();
        if (thisSecondEmail == "") {
            sleep(1000);
            thisSecondEmail = driver2.findElement(By.id("PH_user-email")).getText();
        }
        assertEquals(thisSecondEmail, secondEmailLogin, "should be logged in");

        // check inbox email
        WebElement element = driver2.findElement(By.cssSelector(".letter-list"))
                .findElement(By.xpath("//span[text()='" + subject + "']"));
        assertTrue(element.isDisplayed(), "letter with subject " + subject + "should be in inbox letters list");

        closeSecondDriver(); // todo check driver2 state and move to afterEach
    }
}
