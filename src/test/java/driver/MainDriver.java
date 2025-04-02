package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import model.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import service.*;
import utils.TestListener;

@Slf4j
@Listeners(TestListener.class)
public class MainDriver {

    protected static WebDriver driver;
    protected GmailService gmailService;
    protected User testUser;
    protected EmailMessage testEmail;

    @BeforeSuite
    public void setUpDriver() {
        log.info("Setting up WebDriver and initializing pages...");
        driver = getDriver();
        driver.get("https://www.gmail.com");
        gmailService = new GmailService(driver);

        testUser = UserEmailCreator.getInfoUser();
        testEmail = UserEmailCreator.getInfoMail();
        log.info("WebDriver setup complete.");
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome");
            log.info("Initializing WebDriver for browser: {}", browser);
            switch (browser) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions()
                            .addArguments(
                                    "--disable-notifications",
                                    "--disable-popup-blocking"));
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions()
                            .addArguments(
                                    "--disable-notifications",
                                    "--disable-popup-blocking",
                                    "--incognito"));
                    break;
            }
            driver.manage().window().maximize();
            log.info("WebDriver initialized successfully.");
        }
        return driver;
    }

    @AfterSuite
    public void tearDown() {
        log.info("Logging out and quitting WebDriver...");
        gmailService.logout();
        driver.quit();
        log.info("WebDriver session ended.");
    }
}
