package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static java.util.concurrent.TimeUnit.SECONDS;

public class LoginPage extends Page {

    public LoginPage(WebDriver pDriver) {
        super(pDriver);
    }

    Wait wait = new FluentWait(mDriver).withTimeout(3, SECONDS).pollingEvery(1, SECONDS).ignoring(NoSuchElementException.class);

    @FindBy(xpath = "//i[contains(@class,'icon_mailbox_letter')]")
    public WebElement iconMailbox;

    @FindBy(xpath = "//span[ancestor::div[@id='mailbox']][contains(@class,'mailbox__title__link__text')]")
    public WebElement titleMailbox;

    @FindBy(xpath = "//a[contains(@class, 'mailbox__cloud')]")
    public WebElement iconCloud;

    @FindBy(xpath = "//a[@id='icon_cal']")
    public WebElement iconCalendar;

    @FindBy(id = "mailbox__login")
    public WebElement loginField;

    @FindBy(id = "mailbox__password")
    public WebElement passwordField;

    @FindBy(xpath = "//span[@id='mailbox__auth__button_outer']")
    public WebElement loginButton;

    @FindBy(id = "mailbox:authfail")
    public WebElement authFailText;

    @FindBy(id = "password_warning")
    public WebElement iconWarningPassword;

    @FindBy (xpath="//div[ancestor::div[@id='mailbox-auth-login']][contains(@class,'mailbox__auth__layout__row__col_right')]")
    public WebElement mailboxDomain;

    @FindBy (xpath = "//option[@value='mail.ru']")
    public WebElement dropdownItemMailru;

    @FindBy (xpath = "//option[@value='inbox.ru']")
    public WebElement dropdownItemInboxru;

    @FindBy (xpath = "//option[@value='list.ru']")
    public WebElement dropdownItemListru;

    @FindBy (xpath = "//option[@value='bk.ru']")
    public WebElement dropdownItemBkru;

    @FindBy (id="domain-widget__text")
    public WebElement domainText;

    @FindBy (xpath = "//a[@class=\'mailbox__register__link\']")
    public WebElement linkMailboxRegister;

    @FindBy (xpath = "//a[contains(@class,'mailbox__forget-link')]")
    public WebElement linkForgetPassword;

    @FindBy (xpath = "//input[@id='mailbox__auth__remember__checkbox']")
    public WebElement checkboxRemember;

    @FindBy (xpath = "//label[@class='mailbox__auth__remember']")
    public WebElement labelRemember;

    @FindBy (xpath = "//a[@class='mailbox__register__link']")
    public WebElement linkRegister;

    @FindBy (xpath = "//a[@class='mailbox__register__promo__link']")
    public WebElement promoLinkRegister;

    public boolean areElementsDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(iconMailbox));
        if (iconMailbox.isDisplayed()&&titleMailbox.isDisplayed()&&iconCloud.isDisplayed()&&iconCalendar.isDisplayed()
                &&loginField.isDisplayed()&&passwordField.isDisplayed()&&mailboxDomain.isDisplayed()
                &&loginButton.isDisplayed()&&linkForgetPassword.isDisplayed()&&checkboxRemember.isDisplayed()
                &&labelRemember.isDisplayed()&&linkRegister.isDisplayed()&&promoLinkRegister.isDisplayed()
                ){
            return true;
        } else {
            return false;
        }
    }

    public MailboxMainPage login(final String pLogin, final String pPassword) {
        typeValue(loginField, pLogin);
        typeValue(passwordField, pPassword);
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PH_logoutLink")));
        return PageFactory.initElements(mDriver, MailboxMainPage.class);
    }

    public void login(final String pLogin, final String pPassword, boolean isInvalidData) {
        typeValue(loginField, pLogin);
        typeValue(passwordField, pPassword);
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(getAuthFailText()));
    }

    public void switchMailboxLoginDomain(final String pDomain){
        mailboxDomain.click();
        switch (pDomain) {
            case ("mail.ru"):
                dropdownItemMailru.click();
                break;
            case ("inbox.ru"):
                dropdownItemInboxru.click();
                break;
            case ("list.ru"):
                dropdownItemListru.click();
                break;
            case ("bk.ru"):
                dropdownItemBkru.click();
                break;
        }
    }

    public RegistrationPage openRegistrationPage(){
        linkMailboxRegister.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("b-panel__header__text")));
        return PageFactory.initElements(mDriver, RegistrationPage.class);
    }

    public WebElement getAuthFailText() {
        return authFailText;
    }

    public WebElement getDomainText() {
        return domainText;
    }

    public WebElement getIconWarningPassword() {
        return iconWarningPassword;
    }
}
