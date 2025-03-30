package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static utils.WaitUtils.waitForVisibility;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void clickElement(WebElement element) {
        WebElement el = waitForVisibility(element);
        if (el != null) {
            System.out.println(el.getText() + ", " + el.getTagName() + " was clicked");
            el.click();
        } else {
            throw new NoSuchElementException("Element not found: " + element);
        }
    }


    public void enterText(WebElement element, String text) {
        waitForVisibility(element).sendKeys(text);
    }
}
