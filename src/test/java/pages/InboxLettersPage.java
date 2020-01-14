package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InboxLettersPage {

    public String getUserName(WebDriver driver) {
        return driver.findElement(By.id("PH_user-email")).getText();
    }

    public void clickCreateButton(WebDriver driver) {
        driver.findElement(By.xpath("//span[@class='compose-button__txt']")).click();
    }

    public void typeAdress(WebDriver driver, String emailAddress) {
        driver.findElement(By.xpath("//div[@data-type='to']//input")).sendKeys(emailAddress);
    }

    public void typeSubject(WebDriver driver, String subject) {
        driver.findElement(By.xpath("//input[@name='Subject']")).sendKeys(subject);
    }

    public void typeMessage(WebDriver driver, String text) {
        driver.findElement(By.xpath("//div[@class='cke_widget_editable']/div")).sendKeys(text);
    }

    public void clickSendButton(WebDriver driver) {
        driver.findElement(By.xpath("//span[text()='Отправить']")).click();
    }

    public void clickCloseModalButton(WebDriver driver) {
        driver.findElement(By.xpath("//span[@title='Закрыть']")).click();
    }

    public void openSendLettersPage(WebDriver driver) {
        driver.findElement(By.xpath("//a[@href='/sent/']")).click();
    }
}
