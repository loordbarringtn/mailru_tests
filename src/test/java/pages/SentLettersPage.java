package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.EnvironmentHelper.secondEmailLogin;

public class SentLettersPage {

    public void composeButtonClick (WebDriver driver) {
        driver.findElement(By.xpath("//span[@class='compose-button__txt']")).click();
    }

    public void recepientAddressFill (WebDriver driver, String emailAddress) {
        driver.findElement(By.xpath("//div[@data-type='to']//input")).sendKeys(emailAddress);
    }

    public void subjectFill (WebDriver driver, String subject) {
        driver.findElement(By.xpath("//input[@name='Subject']")).sendKeys(subject);
    }

    public void textFieldFill (WebDriver driver, String text) {
        driver.findElement(By.xpath("//div[@class='cke_widget_editable']/div")).sendKeys(text);
    }

    public void sendButtonClick (WebDriver driver) {
        driver.findElement(By.xpath("//span[text()='Отправить']")).click();
    }

}
