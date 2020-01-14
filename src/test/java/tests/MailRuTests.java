package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.InboxLettersPage;
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
        InboxLettersPage inboxLettersPage = new InboxLettersPage();
        driver.get(baseUrl);

        loginPage.typeLogin(driver, firstEmailLogin);
        loginPage.clickSubmit(driver);
        loginPage.typePassword(driver,firstEmailPassword);
        loginPage.clickSubmit(driver);
        loginPage.verifyLoadingHidden(driver);

        String thisEmail = inboxLettersPage.getUserName(driver);
        assertEquals(thisEmail, firstEmailLogin, "should be logged in");
    }

    @Test
    public void checkEmailSent() {
        LoginPage loginPage = new LoginPage();
        SentLettersPage sentLettersPage = new SentLettersPage();
        InboxLettersPage inboxLettersPage = new InboxLettersPage();
        String subject = getRandomString(15);
        String emailText = getRandomString(50);
        driver.get(baseUrl);

        loginPage.typeLogin(driver, firstEmailLogin);
        loginPage.clickSubmit(driver);
        loginPage.typePassword(driver,firstEmailPassword);
        loginPage.clickSubmit(driver);
        loginPage.verifyLoadingHidden(driver);

        inboxLettersPage.clickCreateButton(driver);
        inboxLettersPage.typeAdress(driver, secondEmailLogin);
        inboxLettersPage.typeSubject(driver, subject);
        inboxLettersPage.typeMessage(driver, emailText);
        inboxLettersPage.clickSendButton(driver);
        inboxLettersPage.clickCloseModalButton(driver);
        inboxLettersPage.openSendLettersPage(driver);

        WebElement element = sentLettersPage.getLetterBySubject(driver, subject);
        assertTrue(element.isDisplayed(), "letter with subject " + subject + "should be in sent letters list");
    }

    @Test
    public void sendEmailTest() throws InterruptedException { // todo refactor authorization to api+cookie
        String subject = getRandomString(15);
        String emailText = getRandomString(50);
        InboxLettersPage inboxLettersPage = new InboxLettersPage();
        SentLettersPage sentLettersPage = new SentLettersPage();
        LoginPage loginPage = new LoginPage();
        driver.get(baseUrl);

        loginPage.typeLogin(driver, firstEmailLogin);
        loginPage.clickSubmit(driver);
        loginPage.typePassword(driver,firstEmailPassword);
        loginPage.clickSubmit(driver);
        loginPage.verifyLoadingHidden(driver);
        String thisEmail = inboxLettersPage.getUserName(driver);
        assertEquals(thisEmail, firstEmailLogin, "should be logged in");

        // send email // todo refactor send message by api

        inboxLettersPage.clickCreateButton(driver);
        inboxLettersPage.typeAdress(driver, secondEmailLogin);
        inboxLettersPage.typeSubject(driver, subject);
        inboxLettersPage.typeMessage(driver, emailText);
        inboxLettersPage.clickSendButton(driver);
        inboxLettersPage.clickCloseModalButton(driver);
        inboxLettersPage.openSendLettersPage(driver);

        startSecondDriver();
        driver2.get(baseUrl);

        loginPage.typeLogin(driver2, secondEmailLogin);
        loginPage.clickSubmit(driver2);

        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("mailbox:password")));

        loginPage.typePassword(driver2,secondEmailPassword);
        loginPage.clickSubmit(driver2);
        loginPage.verifyLoadingHidden(driver2);

        String thisSecondEmail = driver2.findElement(By.id("PH_user-email")).getText();
        if (thisSecondEmail == "") {
            sleep(1000);
            thisSecondEmail = driver2.findElement(By.id("PH_user-email")).getText();
        }
        assertEquals(thisSecondEmail, secondEmailLogin, "should be logged in");

        // check inbox email

        WebElement element = sentLettersPage.getLetterBySubject(driver2,subject);
        assertTrue(element.isDisplayed(), "letter with subject " + subject + "should be in inbox letters list");

        closeSecondDriver(); // todo check driver2 state and move to afterEach
    }
}
