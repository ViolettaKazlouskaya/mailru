package mailrutests;

import basic.BasicTestCase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import pages.LoginPage;
import pages.MailboxMainPage;
import pages.RegistrationPage;
import utils.ConfigProperties;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class LoginTest extends BasicTestCase {

    private LoginPage loginPage = PageFactory.initElements(mDriver, LoginPage.class);
    private MailboxMainPage mailboxMainPage;
    private RegistrationPage registrationPage;
    private String mLogin="vetik.18";

    public LoginTest() {
        super("Chrome");
    }

    @Before
    public void setUp(){
        mDriver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("imp.wait")), TimeUnit.SECONDS);
        mDriver.get(ConfigProperties.getProperty("base.url"));
    }

    @Test
    public void testLoadAuthorizationForm(){
        assertTrue(loginPage.areElementsDisplayed());
    }

    @Test
    public void testValidLoginData(){
        mailboxMainPage = loginPage.login(mLogin,"v6555170");
        assertTrue(mailboxMainPage.getLogoutLink().isDisplayed());
        mailboxMainPage.logout();
    }

    @Test
    public void testEmptyLoginData(){
        loginPage.login("","", true);
        assertEquals("Введите имя ящика", loginPage.getAuthFailText().getText());
    }

    @Test
    public void testEmptyPassword(){
        loginPage.login(mLogin,"", true);
        assertEquals("Введите пароль", loginPage.getAuthFailText().getText());
    }

    @Test
    public void testInvalidPassword(){
        loginPage.login(mLogin,"123", true);
        assertEquals("Неверное имя или пароль",loginPage.getAuthFailText().getText());
    }

    @Test
    public void testPasswordInCyrillic(){
        loginPage.login(mLogin,"фыва", true);
        assertTrue(loginPage.getIconWarningPassword().isDisplayed());
    }

    @Test
    public void testSwitchMailboxLoginDomain(){
        loginPage.switchMailboxLoginDomain("mail.ru");
        assertEquals("@mail.ru",loginPage.getDomainText().getText());
        loginPage.switchMailboxLoginDomain("inbox.ru");
        assertEquals("@inbox.ru",loginPage.getDomainText().getText());
        loginPage.switchMailboxLoginDomain("list.ru");
        assertEquals("@list.ru",loginPage.getDomainText().getText());
        loginPage.switchMailboxLoginDomain("bk.ru");
        assertEquals("@bk.ru",loginPage.getDomainText().getText());
    }

    @Test
    public void testFollowRegistrationLink (){
        registrationPage = loginPage.openRegistrationPage();
        assertEquals("Регистрация", registrationPage.getHeaderRegistration().getText());
    }
}
