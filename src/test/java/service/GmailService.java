package service;

import model.EmailMessage;
import model.User;
import org.openqa.selenium.WebDriver;
import pages.DraftPage;
import pages.LoginPage;
import pages.MainPage;
import pages.SentPage;


public class GmailService {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private DraftPage draftPage;
    private SentPage sentPage;

    public GmailService(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.mainPage = new MainPage(driver);
        this.draftPage = new DraftPage(driver);
        this.sentPage = new SentPage(driver);
    }

    public void login(User user) {
        loginPage.login(user.getEmail(), user.getPassword());
        boolean isLoggedIn = loginPage.isAccountPresent(user.getEmail());
        if (!isLoggedIn) {
            throw new RuntimeException("Login failed!");
        }
    }

    public boolean isAccountPresent(String email) {
        return loginPage.isAccountPresent(email);
    }

    public void logout() {
        mainPage.logout();
    }

    public void createDraft(EmailMessage email) {
        draftPage.createDraft(email.getRecipient(), email.getSubject(), email.getBody());
    }

    public boolean isDraftPresent(EmailMessage email) {
        mainPage.openDrafts();
        return draftPage.isDraftPresent(email.getRecipient(), email.getSubject(), email.getBody());
    }

    public boolean isDraftCorrect(EmailMessage email) {
        return draftPage.isDraftCorrect(email.getRecipient(), email.getSubject(), email.getBody());
    }

    public void sendDraft(EmailMessage email) {
        sentPage.sendDraft(email.getRecipient(), email.getSubject(), email.getBody());
    }

    public boolean isSentPresent(EmailMessage email) {
        mainPage.openSent();
        return sentPage.isSentPresent(email.getRecipient(), email.getSubject(), email.getBody());
    }
}
