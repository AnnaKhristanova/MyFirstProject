package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.tests.TestBase;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {


    @BeforeMethod(alwaysRun = true)
    public void startMailServer() {
        app.mail().start();
    }

    @Test

    public void changePassword() throws IOException {
        app.panel().login("administrator", "root");
        app.panel().getUser();
        String email = app.panel().getEmail();
        String realname = app.panel().getRealname();
        String username = app.panel().getUsername();
        app.panel().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 100000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.panel().finish(confirmationLink);
        app.panel().changePassword(realname, "passwordNew", "passwordNew");
        HttpSession session = app.newSession();
        session.login(username, "passwordNew");
        assertTrue(session.isLoggedInAs(username));
        app.panel().login(username, "passwordNew");
    }

    private String  findConfirmationLink(List<MailMessage> mailMessages, String email) {

        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();

        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}