package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static helpers.EnvironmentHelper.browser;
import static helpers.EnvironmentHelper.loadEnv;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestBase {
    WebDriver driver;
    WebDriver driver2;
    WebDriverWait wait;
    WebDriverWait wait2;
    String baseUrl;

    @BeforeAll
    public static void start() {
        loadEnv();
    }

    @BeforeEach
    public void setUp() {
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        baseUrl = "https://mail.ru";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
//        if driver2.
    }

    public void startSecondDriver() {
        switch (browser) {
            case "chrome":
                driver2 = new FirefoxDriver();
                break;
            case "firefox":
                driver2 = new ChromeDriver();
                break;
            default:
                driver2 = new FirefoxDriver();
        }
        wait2 = new WebDriverWait(driver2, 20);

    }

    public void closeSecondDriver() {
        driver2.quit();
    }

}
