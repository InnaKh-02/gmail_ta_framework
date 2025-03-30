package utils;

import driver.MainDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private static WebDriver driver = MainDriver.getDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static void waitWithJsExecutor(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");
    }

    public static WebElement waitForVisibility(WebElement element) {
        try {
            return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
        } catch (TimeoutException | StaleElementReferenceException e) {
            return null;
        }
    }

    public static List<WebElement> waitForVisibility(List<WebElement> elements) {
        try {
            boolean isFound = wait.until(driver -> !elements.isEmpty());
            if(isFound){
                return elements;
            }
            return null;
        } catch (TimeoutException | StaleElementReferenceException e) {
            return null;
        }
    }

    public static WebElement waitForVisibilityBy(By by) {
        try {
            return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
        } catch (TimeoutException | StaleElementReferenceException e) {
            return null;
        }
    }

}
