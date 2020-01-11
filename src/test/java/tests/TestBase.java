package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static helpers.EnvironmentHelper.loadEnv;

public class TestBase {
    WebDriver driver;
    WebDriver driver2;
    WebDriverWait wait;
    String baseUrl;

    @BeforeAll
    public static void start() {
        loadEnv();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver2 = new FirefoxDriver();
        baseUrl = "https://mail.ru";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 20);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
