package mailrutests;

import basic.BasicTestCase;
import logger.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pages.CaptchaPage;
import pages.RegistrationPage;
import utils.ConfigProperties;
import utils.ExcelUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

public class RegistrationTest extends BasicTestCase {

    private RegistrationPage registrationPage = PageFactory.initElements(mDriver, RegistrationPage.class);
    private CaptchaPage captchaPage = PageFactory.initElements(mDriver, CaptchaPage.class);
    private ArrayList<String> incorrectMailbox;
    Wait wait = new FluentWait(mDriver).withTimeout(3, SECONDS).pollingEvery(1, SECONDS).ignoring(NoSuchElementException.class);

    public RegistrationTest() {
        super("Chrome");
    }


    @Before
    public void setUp(){
        mDriver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("imp.wait")), TimeUnit.SECONDS);
        mDriver.get(ConfigProperties.getProperty("registration.url"));
    }

    @Test
    public void testLoadRegistrationForm(){
        assertTrue(registrationPage.areElementsDisplayed());
    }

    @Test
    public void testMaxLengthFirstName(){
        Logger.info("Check First Name Field with max length name (40 chars)");
        String mFirstName="qwertqwertqwertqwertqwertqwertqwertqwert";
        registrationPage.setFirstName(mFirstName);
        assertEquals(mFirstName, registrationPage.getFirstNameField().getAttribute("value"));
    }

    @Test
    public void testUnacceptableLengthFirstName(){
        Logger.info("Check First Name Field with max+1 length name (41 chars)");
        registrationPage.setFirstName("qwertqwertqwertqwertqwertqwertqwertqwertq");
        assertEquals("qwertqwertqwertqwertqwertqwertqwertqwert",registrationPage.getFirstNameField().getAttribute("value"));
    }

    @Test
    public void testSpecialSymbolsInFirstName(){
        Logger.info("Check First Name Field with special symbols in name");
        registrationPage.setFirstName("&&<>");
        registrationPage.makeNotActive();
        assertEquals("Некорректное имя",registrationPage.getFirstNameError().getText());
        registrationPage.setFirstName("!@#$%^+=?");
        registrationPage.makeNotActive();
        assertEquals("Некорректное имя",registrationPage.getFirstNameError().getText());
    }

    @Test
    public void testDigitsInFirstName(){
        Logger.info("Check First Name Field with digits in name");
        registrationPage.setFirstName("1234567890");
        registrationPage.makeNotActive();
        assertEquals("Некорректное имя",registrationPage.getFirstNameError().getText());
    }

    @Test
    public void testMaxLengthLastName(){
        Logger.info("Check Last Name Field with max length last name (40 chars)");
        String mLastName="qwertqwertqwertqwertqwertqwertqwertqwert";
        registrationPage.setLastName(mLastName);
        assertEquals(mLastName, registrationPage.getLastNameField().getAttribute("value"));
    }

    @Test
    public void testUnacceptableLengthLastName(){
        Logger.info("Check Last Name Field with max+1 length last name (41 chars)");
        registrationPage.setLastName("qwertqwertqwertqwertqwertqwertqwertqwertq");
        assertEquals("qwertqwertqwertqwertqwertqwertqwertqwert",registrationPage.getLastNameField().getAttribute("value"));
    }

    @Test
    public void testSpecialSymbolsInLastName(){
        Logger.info("Check Last Name Field with special symbols in last name");
        registrationPage.setLastName("&&<>");
        registrationPage.makeNotActive();
        assertEquals("Некорректная фамилия",registrationPage.getLastNameError().getText());
        registrationPage.setLastName("!@#$%^+=?");
        registrationPage.makeNotActive();
        assertEquals("Некорректная фамилия",registrationPage.getLastNameError().getText());
    }

    @Test
    public void testDigitsInLastName(){
        Logger.info("Check First Name Field with digits in last name");
        registrationPage.setLastName("1234567890");
        registrationPage.makeNotActive();
        assertEquals("Некорректная фамилия",registrationPage.getLastNameError().getText());
    }

    @Test
    public void testNonexistentDateOfBirthday(){
        registrationPage.setDateOfBirthday("31","Февраль","2017");
        assertEquals("28",registrationPage.getSelectedDayOfBirthday().getText());
        assertEquals("Февраль",registrationPage.getSelectedMonthOfBirthday().getText());
        assertEquals("2017",registrationPage.getSelectedYearOfBirthday().getText());
    }

    @Test
    public void testDateOfBirthdayInFuture(){
        registrationPage.setDateOfBirthday("31","Декабрь","2017");
        registrationPage.registerUser();
        assertTrue(registrationPage.getBirthdayErrorMessage().getText().contains("Машину времени еще не изобрели, Марти, выбери другую дату"));
    }

    @Test
    public void testShortDesiredMailbox(){
        registrationPage.setDesiredMailbox("v", false);
        registrationPage.makeNotActive();
        assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("Некорректное имя почтового ящика"));
        assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("Извините, имя почтового ящика должно быть длиной от 4 до 31 символов"));
    }

    @Test
    public void testDesiredMailboxWithCyrillic(){
        registrationPage.setDesiredMailbox("1qйцукен", true);
        assertTrue(registrationPage.getVacantEmailMessage().getText().contains("В имени почтового ящика нельзя использовать кириллицу"));
        registrationPage.makeNotActive();
        assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("В имени почтового ящика нельзя использовать кириллицу"));
    }

    @Test
    public void testDesiredMailboxWithSpecialSymbols(){
        registrationPage.setDesiredMailbox("!@#$%^&*()+=<>?/", true);
        assertTrue(registrationPage.getVacantEmailMessage().getText().contains("Некорректное имя почтового ящика. Допустимо использовать только латинские буквы, цифры,"));
        assertTrue(registrationPage.getVacantEmailMessage().getText().contains("знак подчеркивания («_»), точку («.»), минус («-»)"));
        registrationPage.makeNotActive();
        assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("Некорректное имя почтового ящика. Допустимо использовать только латинские буквы, цифры,"));
        assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("знак подчеркивания («_»), точку («.»), минус («-»)"));
    }

    @Test
    public void testDesiredMailboxIncorrectFirstChar(){
        incorrectMailbox = new ArrayList<String>();
        incorrectMailbox.add(".qwe12345");
        incorrectMailbox.add("_125irnjnv");
        incorrectMailbox.add("-547fhviv");
        for (String mDesiredMailbox:incorrectMailbox){
            registrationPage.setDesiredMailbox(mDesiredMailbox, true);
            assertTrue(registrationPage.getVacantEmailMessage().getText().contains("Некорректное имя почтового ящика."));
            assertTrue(registrationPage.getVacantEmailMessage().getText().contains("Имя должно начинаться с латинской буквы (a‑z) или цифры (0‑9)"));
            registrationPage.makeNotActive();
            assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("Некорректное имя почтового ящика."));
            assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("Имя должно начинаться с латинской буквы (a‑z) или цифры (0‑9)"));

        }
    }

    @Test
    public void testDesiredMailboxIncorrectLastChar(){
        incorrectMailbox = new ArrayList<String>();
        incorrectMailbox.add("qwe12345.");
        incorrectMailbox.add("125irnjnv_");
        incorrectMailbox.add("547fhviv-");
        for (String mDesiredMailbox:incorrectMailbox){
            registrationPage.setDesiredMailbox(mDesiredMailbox, true);
            assertTrue(registrationPage.getVacantEmailMessage().getText().contains("Некорректное имя почтового ящика."));
            assertTrue(registrationPage.getVacantEmailMessage().getText().contains("Имя должно заканчиваться на латинскую букву (a‑z) или цифру (0‑9)"));
            registrationPage.makeNotActive();
            assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("Некорректное имя почтового ящика."));
            assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("Имя должно заканчиваться на латинскую букву (a‑z) или цифру (0‑9)"));

        }
    }

    @Test
    public void testIncorrectDesiredMailbox(){
        incorrectMailbox = new ArrayList<String>();
        incorrectMailbox.add("qwe12..345");
        incorrectMailbox.add("125ir__njnv");
        incorrectMailbox.add("547f--hviv");
        for (String mDesiredMailbox:incorrectMailbox){
            registrationPage.setDesiredMailbox(mDesiredMailbox, true);
            assertTrue(registrationPage.getVacantEmailMessage().getText().contains("Ящик с таким именем уже существует"));
            registrationPage.makeNotActive();
            assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("Ящик с таким именем уже существует"));
        }
    }

    @Test
    public void testMaxLengthDesiredMailbox(){
        String mailboxWith31Symbols = "123456789.qwertqwert_12345678-9";
        registrationPage.setDesiredMailbox(mailboxWith31Symbols, false);
        assertEquals(mailboxWith31Symbols,registrationPage.getDesiredMailboxField().getAttribute("value"));
    }

    @Test
    public void testUnacceptableLengthDesiredMailbox(){
        registrationPage.setDesiredMailbox("123456789.qwertqwert_12345678-9q", false);
        assertEquals("123456789.qwertqwert_12345678-9",registrationPage.getDesiredMailboxField().getAttribute("value"));
    }

    @Test
    public void testExistentDesiredMailbox(){
        registrationPage.setDesiredMailbox("vetik.18","@mail.ru", true);
        assertTrue(registrationPage.getVacantEmailMessage().getText().contains("Ящик с таким именем уже существует."));
        assertTrue(registrationPage.getVacantEmailMessage().getText().contains("Возможно, вам понравятся имена:"));
        assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("Ящик с таким именем уже существует"));
        String mSelectedMailbox = registrationPage.selectVacantMailbox();
        assertEquals(mSelectedMailbox, registrationPage.getDesiredMailboxField().getAttribute("value")+registrationPage.getTextMailboxDomain().getText());
    }

    @Test
    public void testShortPassword(){
        registrationPage.setPassword("1");
        assertTrue(registrationPage.getPasswordTooltip().isDisplayed());
        assertTrue(registrationPage.getProgressbarPasswordStatus().isDisplayed());
        assertEquals("Ненадежный пароль", registrationPage.getTextPasswordStatus().getText());
        assertEquals("Пароль слишком короткий", registrationPage.getTextStatusErrorReason().getText());
    }

    @Test
    public void testOnlyDigitsPassword(){
        registrationPage.setPassword("123456");
        assertTrue(registrationPage.getPasswordTooltip().isDisplayed());
        assertTrue(registrationPage.getProgressbarPasswordStatus().isDisplayed());
        assertEquals("Ненадежный пароль", registrationPage.getTextPasswordStatus().getText());
        String mTextStatusErrorReason = registrationPage.getTextStatusErrorReason().getText();
        assertTrue(mTextStatusErrorReason.contains("Пароль легко подобрать"));
        assertTrue(mTextStatusErrorReason.contains("Пароль не должен состоять только из цифр"));
        assertTrue(mTextStatusErrorReason.contains("Пароль недостаточно безопасный"));
    }

    @Test
    public void testCyrillicCharsPassword(){
        registrationPage.setPassword("2фывап1");
        assertTrue(registrationPage.getPasswordTooltip().isDisplayed());
        assertTrue(registrationPage.getProgressbarPasswordStatus().isDisplayed());
        assertEquals("Ненадежный пароль", registrationPage.getTextPasswordStatus().getText());
        assertTrue(registrationPage.getTextStatusErrorReason().getText().contains("Пароль не должен содержать кириллические символы"));
    }

    @Test
    public void testInsecurePassword(){
        registrationPage.setPassword("2hf4j78");
        assertTrue(registrationPage.getPasswordTooltip().isDisplayed());
        assertTrue(registrationPage.getProgressbarPasswordStatus().isDisplayed());
        assertEquals("Средний пароль", registrationPage.getTextPasswordStatus().getText());
        assertTrue(registrationPage.getTextStatusErrorReason().getText().contains("Пароль недостаточно безопасный"));
    }

    @Test
    public void testSecurePassword(){
        registrationPage.setPassword("Q2hf4j7Z!U5@1#4$%5^&t*(n)_+m=d<>?/");
        assertTrue(registrationPage.getPasswordTooltip().isDisplayed());
        assertTrue(registrationPage.getProgressbarPasswordStatus().isDisplayed());
        assertEquals("Надежный пароль", registrationPage.getTextPasswordStatus().getText());
        assertTrue(registrationPage.getTextStatusErrorReason().getText().contains("Теперь пароль не подобрать и ваша почта надёжно защищена"));
    }

    @Test
    public void testIconPasswordEye() throws IOException {
        registrationPage.setPassword("1q2w3e4rT+!");
        registrationPage.setPasswordVisibility(true);
        captureScreen("testIconPasswordEye-PasswordIsVisible.png");
        assertTrue(registrationPage.getIconPasswordEye().getAttribute("class").contains("b-password__eye_active"));
        registrationPage.setPasswordVisibility(false);
        captureScreen("testIconPasswordEye-PasswordIsInvisible.png");
        assertFalse(registrationPage.getIconPasswordEye().getAttribute("class").contains("b-password__eye_active"));
    }

    @Test
    public void testMaxLengthPassword(){
        String mPassword="123456789q123456789q123456789q123456789q";
        registrationPage.setPassword(mPassword);
        assertEquals(mPassword, registrationPage.getPasswordField().getAttribute("value"));
    }

    @Test
    public void testUnacceptableLengthPassword(){
        registrationPage.setPassword("123456789q123456789q123456789q123456789qz");
        assertEquals("123456789q123456789q123456789q123456789q", registrationPage.getPasswordField().getAttribute("value"));
    }

    @Test
    public void testPasswordConfirmation(){
        registrationPage.setPassword("qwe45678");
        registrationPage.setPasswordConfirmation("qwe4567");
        registrationPage.makeNotActive();
        assertTrue(registrationPage.getHintAboutIncorrectRepassword().isDisplayed());
        registrationPage.setPasswordConfirmation("qwe456789");
        assertTrue(registrationPage.getHintAboutIncorrectRepassword().isDisplayed());
        registrationPage.setPasswordConfirmation("qwe45678");
        assertFalse(registrationPage.getHintAboutIncorrectRepassword().isDisplayed());
    }

    @Test
    public void testLongPhoneNumber(){
        registrationPage.setPhoneNumber("+3752912345678");
        assertTrue(registrationPage.getPhoneMessage().getText().contains("Вы ввели 1 лишнюю цифру"));
        registrationPage.makeNotActive();
        assertTrue(registrationPage.getHintPhoneError().getText().contains("Вы указали некорректный номер телефона"));
    }

    @Test
    public void testIncorrectPhoneNumber(){
        registrationPage.setPhoneNumber("+72913");
        assertTrue(registrationPage.getPhoneMessage().getText().contains("Данный телефон не поддерживается"));
        registrationPage.makeNotActive();
        assertTrue(registrationPage.getHintPhoneError().getText().contains("Вы указали некорректный номер телефона"));
    }

    @Test
    public void testNonDigitsInPhoneNumber(){
        registrationPage.setPhoneNumber("+qw()123fs+!@#фыв45");
        assertEquals("+12345",registrationPage.getPhoneField().getAttribute("value"));
    }

    @Test
    public void testInputWithoutPlusPhoneNumber(){
        registrationPage.setPhoneNumber("293190955");
        assertTrue(registrationPage.getPhoneMessage().getText().contains("Префикс заменен на +7 для страны Россия"));
        assertEquals("+7293190955",registrationPage.getPhoneField().getAttribute("value"));
    }

    @Test
    public void testShortPhoneNumber(){
        registrationPage.setPhoneNumber("+37529");
        assertTrue(registrationPage.getPhoneMessage().getText().contains("Осталось ввести 7 цифр"));
        registrationPage.makeNotActive();
        assertTrue(registrationPage.getHintPhoneError().getText().contains("Вы указали некорректный номер телефона"));
    }

    @Test
    public void testEmptyDataRegistration(){
        registrationPage.registerUser();
        assertTrue(registrationPage.getFirstNameError().getText().contains("Укажите имя"));
        assertTrue(registrationPage.getLastNameError().getText().contains("Укажите фамилию"));
        assertTrue(registrationPage.getBirthdayErrorMessage().getText().contains("Укажите дату рождения"));
        assertTrue(registrationPage.getGenderErrorMessage().getText().contains("Укажите ваш пол"));
        assertTrue(registrationPage.getMailboxErrorMessage().getText().contains("Укажите желаемый почтовый адрес"));
        assertTrue(registrationPage.getPasswordErrorMessage().getText().contains("Укажите пароль"));
    }

    @Test
    public void testBelowMaxPassword(){
        String mPassword="123456789q123456789q123456789q12345678";
        registrationPage.setPassword(mPassword);
        assertEquals(mPassword, registrationPage.getPasswordField().getAttribute("value"));
    }

    @Test
    public void registrationWithValidData() throws Exception {
        String mPath = "D://mailruRegistration.xlsx";
        ExcelUtil.setExcelFile(mPath, "ValidData");
        int numOfRows = ExcelUtil.getNumOfRows();

        for(int i = 1; i <= numOfRows; ++i) {
            if (i!=1) {
                setUp();
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = mDriver.switchTo().alert();
                alert.accept();
            }
            registrationPage.executeUsersRegistration(i);

            if (captchaPage.captchaField == null) {
                ExcelUtil.setCellData("Unpass", i, 12, mPath);
            } else {
                ExcelUtil.setCellData("Pass", i, 12, mPath);
            }
        }
    }
}
