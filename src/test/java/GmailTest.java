import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import driver.MainDriver;

import static org.testng.Assert.*;

@Slf4j
public class GmailTest extends MainDriver {

    @Test(priority = 1)
    public void loginTest() {
        log.info("Starting login test...");
        gmailService.login(testUser);
        boolean isLoggedIn = gmailService.isAccountPresent(testUser.getEmail());
        log.info("Login test result: {}", isLoggedIn);
        assertTrue(isLoggedIn, "Login failed!");
    }

    @Test(priority = 2)
    public void isMailPresentInDraftTest() {
        log.info("Creating draft email...");
        gmailService.createDraft(testEmail);
        boolean isDraftPresent = gmailService.isDraftPresent(testEmail);
        log.info("Draft presence check result: {}", isDraftPresent);
        assertTrue(isDraftPresent, "Draft email is not present!");
    }

    @Test(priority = 3)
    public void areDraftContentsCorrectTest() {
        log.info("Verifying draft email contents...");
        boolean isDraftCorrect = gmailService.isDraftCorrect(testEmail);
        log.info("Draft content verification result: {}", isDraftCorrect);
        assertTrue(isDraftCorrect, "Draft email contents are incorrect!");
    }

    @Test(priority = 4)
    public void draftIsAbsentTest() {
        log.info("Sending draft email...");
        gmailService.sendDraft(testEmail);
        boolean isDraftStillPresent = gmailService.isDraftPresent(testEmail);
        log.info("Draft presence after sending result: {}", isDraftStillPresent);
        assertFalse(isDraftStillPresent, "Draft email is still present after sending!");
    }

    @Test(priority = 5)
    public void draftInSentTest() {
        log.info("Checking if email is in Sent folder...");
        boolean isSentPresent = gmailService.isSentPresent(testEmail);
        log.info("Sent email presence check result: {}", isSentPresent);
        assertTrue(isSentPresent, "Sent email is not found in Sent folder!");
    }
}
