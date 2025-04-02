package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import static utils.WaitUtils.waitForVisibilityBy;

public class LoginPage extends BasePage {
    @FindBy(id = "identifierId")
    private WebElement emailField;
    @FindBy(id = "identifierNext")
    private WebElement emailButton;
    @FindBy(name = "Passwd")
    private WebElement passwordField;
    @FindBy(id = "passwordNext")
    private WebElement passwordButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        enterText(emailField, email);
        clickElement(emailButton);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        enterText(passwordField, password);
        clickElement(passwordButton);
    }

    public boolean isAccountPresent(String email) {
        try {
            WebElement emailElement = waitForVisibilityBy(By.xpath(
                    "//div[contains(text(), '" + email + "')]"));
            return emailElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
