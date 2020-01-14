package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SentLettersPage {
    public WebElement getLetterBySubject(WebDriver driver, String subject) {
        return driver.findElement(By.cssSelector(".letter-list"))
                .findElement(By.xpath("//span[text()='" + subject + "']"));
    }
}
