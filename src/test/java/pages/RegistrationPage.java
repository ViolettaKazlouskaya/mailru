package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import utils.ExcelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

public class RegistrationPage extends Page {

    public RegistrationPage(WebDriver pDriver) {
        super(pDriver);
    }

    Wait wait = new FluentWait(mDriver).withTimeout(3, SECONDS).pollingEvery(1, SECONDS).ignoring(NoSuchElementException.class);

    private WebElement mSelectedEmail;

    @FindBy(className = "b-header__main")
    public WebElement headerMain;

    @FindBy(className = "b-panel__header__text")
    public WebElement headerRegistration;

    @FindBy(xpath = "//label[ancestor::div[@data-field-name='firstname']]")
    public WebElement labelFirstName;

    @FindBy(name = "firstname")
    public WebElement firstNameField;

    @FindBy(xpath = "//div[ancestor::div[@data-field-name='firstname']][contains(@class,'b-form-field__errors__error')][contains(@class,'visible')]")
    public WebElement firstNameError;

    @FindBy(xpath = "//label[ancestor::div[@data-field-name='lastname']]")
    public WebElement labelLastName;

    @FindBy(name = "lastname")
    public WebElement lastNameField;

    @FindBy(xpath = "//div[ancestor::div[@data-field-name='lastname']][contains(@class,'b-form-field__errors__error')][contains(@class,'visible')]")
    public WebElement lastNameError;

    @FindBy(xpath = "//label[contains(@for,'password_')]")
    public WebElement labelPassword;

    @FindBy(xpath = "//input[contains(@id,'password_')]")
    public WebElement passwordField;

    @FindBy(xpath = "//div[contains(@class,'b-password__eye')]")
    public WebElement iconPasswordEye;

    @FindBy(className = "b-password__tooltip")
    public WebElement passwordTooltip;

    @FindBy(className = "b-password__status-progress")
    public WebElement progressbarPasswordStatus;

    @FindBy(xpath = "//span[contains (@class, 'b-password__status-text_')]")
    public WebElement textPasswordStatus;

    @FindBy(className = "b-password__reasons")
    public WebElement textStatusErrorReason;

    @FindBy(xpath = "//div[ancestor::div[@data-field-name='password']][contains(@class,'b-form-field__errors__error')][contains(@class,'visible')]")
    public WebElement passwordErrorMessage;

    @FindBy(id = "passwordRetry")
    public WebElement passwordConfirmationField;

    @FindBy(xpath = "//div[contains(@class, 'b-form-field__errors__error')][contains(@class, 'js-retryfor')]")
    public WebElement hintAboutIncorrectRepassword;

    @FindBy(xpath = "//label[ancestor::div[@data-field-name=\"birthdate\"]]")
    public WebElement labelBirthday;

    @FindBy(xpath = "//*[ancestor::div[@class='b-date__day']]/div[contains(@class,'b-dropdown__ctrl')]")
    public WebElement dayOfBirthday;

    @FindBy(xpath = "//*[ancestor::div[@class=\"b-date__day\"]]/span[contains(@class,\"b-dropdown__ctrl__text\")]")
    public WebElement selectedDayOfBirthday;

    @FindBy(xpath = "//*[ancestor::div[@class='b-date__day']]/div[contains(@class,'b-dropdown__list')]")
    public WebElement dropdownListDay;

    @FindBy(xpath = "//*[ancestor::div[@class='b-date__month']]/div[contains(@class,'b-dropdown__ctrl')]")
    public WebElement monthOfBirthday;

    @FindBy(xpath = "//*[ancestor::div[@class='b-date__month']]/span[contains(@class,'b-dropdown__ctrl__text')]")
    public WebElement selectedMonthOfBirthday;

    @FindBy(xpath = "//*[ancestor::div[@class='b-date__month']]/div[contains(@class,'b-dropdown__list')]")
    public WebElement dropdownListMonth;

    @FindBy(xpath = "//*[ancestor::div[@class='b-date__year']]/div[contains(@class,'b-dropdown__ctrl')]")
    public WebElement yearOfBirthday;

    @FindBy(xpath = "//*[ancestor::div[@class='b-date__year']]/span[contains(@class,'b-dropdown__ctrl__text')]")
    public WebElement selectedYearOfBirthday;

    @FindBy(xpath = "//*[ancestor::div[@class='b-date__year']]/div[contains(@class,'b-dropdown__list')]")
    public WebElement dropdownListYear;

    @FindBy(xpath = "//div[ancestor::div[@data-field-name='birthdate']][contains(@class,'b-form-field__errors__error')][contains(@class,'visible')]")
    public WebElement birthdayErrorMessage;

    @FindBy(xpath = "//div[ancestor::div[@data-field-name='sex']][contains(@class,'b-form-field__errors__error')][contains(@class,'visible')]")
    public WebElement genderErrorMessage;

    @FindBy(xpath = "//*[ancestor::div[@data-field-name='email']]/label[contains(@for,'undefined')]")
    public WebElement labelDesiredMailbox;

    @FindBy(xpath = "//span[@class='b-email__name']/input")
    public WebElement desiredMailboxField;

    @FindBy(xpath = "//*[ancestor::span[@class='b-email__domain']]/div[contains(@class,'b-dropdown__ctrl')]")
    public WebElement desiredMailboxDomain;

    @FindBy(xpath = "//*[ancestor::span[@class='b-email__domain']]/span[contains(@class,'b-dropdown__ctrl__text')]")
    public WebElement textMailboxDomain;

    @FindBy(xpath = "//*[ancestor::span[@class='b-email__domain']]/div[contains(@class,'b-dropdown__list')]")
    public WebElement dropdownListMailboxDomain;

    @FindBy(xpath = "//div[ancestor::div[@data-field-name='email']][contains(@class,'b-form-field__errors')][contains(@class,'visible')]")
    public WebElement mailboxErrorMessage;

    @FindBy(xpath = "//div[@class='b-vacant-email__message']")
    public WebElement vacantEmailMessage;

    @FindBy(xpath = "//div[@class='b-vacant-email__tooltip']")
    public WebElement vacantEmailsTooltip;

    @FindBy(xpath = "//label[contains(@for,'phone_')]")
    public WebElement labelPhone;

    @FindBy(xpath = "//*[ancestor::div[@name='phone']]/div[@class='b-dropdown__ctrl']")
    public WebElement phoneCountry;

    @FindBy(xpath = "//input[@name='phone.phone']")
    public WebElement phoneField;

    @FindBy(xpath = "//div[@class='b-cellphone__message']")
    public WebElement phoneMessage;

    @FindBy(xpath = "//*[ancestor::div[@data-field-name='phone']]/div[contains(@class,'b-form-field__errors__error')][contains(@class,'visible')]")
    public WebElement hintPhoneError;

    @FindBy(xpath = "//*[ancestor::div[contains(@class,'b-form__controls_responsive')]]/button[contains(@class,'btn_main')]")
    public WebElement registerButton;

    @FindBy(xpath = "//label[ancestor::div[@data-field-name='sex']][contains(@for, 'undefined')]")
    public WebElement labelGender;

    @FindBy(xpath = "//span[ancestor::label[@data-mnemo='sex-male']][contains(@class, 'b-radiogroup__text-wrap')]")
    public WebElement labelMaleGender;

    @FindBy(xpath = "//span[ancestor::label[@data-mnemo='sex-male']][@class='b-radiogroup__input-wrapper']")
    public WebElement radiobuttonMaleGender;

    @FindBy(xpath = "//span[ancestor::label[@data-mnemo='sex-female']][contains(@class, 'b-radiogroup__text-wrap')]")
    public WebElement labelFemaleGender;

    @FindBy(xpath = "//span[ancestor::label[@data-mnemo='sex-female']][@class='b-radiogroup__input-wrapper']")
    public WebElement radiobuttonFemaleGender;

    @FindBy(xpath = "//div[ancestor::div[@data-field-name='phone']][contains(@class,'b-form-field__message')]")
    public WebElement messageAfterPhoneNumber;

    @FindBy(xpath = "//*[ancestor::div[@data-field-name='phone']]/a[contains(@class,'js-signup-simple-link')]")
    public WebElement linkNoPhone;

    @FindBy(xpath = "//input[ancestor::div[@name='additional_email']][ancestor::span[@class='b-email__name']]")
    public WebElement additionalEmailField;

    @FindBy(xpath = "//div[ancestor::div[contains(@class,'b-form__controls_responsive')]][contains(@class,'message')]")
    public WebElement messageAboutUserAgreement;

    @FindBy(xpath = "//a[ancestor::div[contains(@class,'b-form__controls_responsive')]]")
    public WebElement linkUserAgreement;

    public boolean areElementsDisplayed(){
        if(headerRegistration.isDisplayed()&&labelFirstName.isDisplayed()&&firstNameField.isDisplayed()
                &&labelLastName.isDisplayed()&&lastNameField.isDisplayed()&&labelBirthday.isDisplayed()
                &&dayOfBirthday.isDisplayed()&&monthOfBirthday.isDisplayed()&&yearOfBirthday.isDisplayed()
                &&labelGender.isDisplayed()&&labelMaleGender.isDisplayed()&&radiobuttonMaleGender.isDisplayed()
                &&labelFemaleGender.isDisplayed()&&radiobuttonFemaleGender.isDisplayed()
                &&labelDesiredMailbox.isDisplayed()&&desiredMailboxField.isDisplayed()
                &&desiredMailboxDomain.isDisplayed()&&labelPassword.isDisplayed()&&passwordField.isDisplayed()
                &&iconPasswordEye.isDisplayed()&&labelPhone.isDisplayed()&&phoneCountry.isDisplayed()
                &&phoneField.isDisplayed()&&messageAfterPhoneNumber.isDisplayed()&&linkNoPhone.isDisplayed()
                &&registerButton.isDisplayed()&&messageAboutUserAgreement.isDisplayed()&&linkUserAgreement.isDisplayed()){
            return true;
        }else{
            return false;
        }
    }

    public void setFirstName(final String pFirstName) {
        typeValue(firstNameField, pFirstName);
    }

    public void setLastName(final String pLastName) {
        typeValue(lastNameField, pLastName);
    }

    public void setDateOfBirthday(final String pDay, final String pMonth, final String pYear) {
        dayOfBirthday.click();
        dropdownListDay.findElement(By.xpath("//a[@data-text='" + pDay + "']")).click();
        monthOfBirthday.click();
        dropdownListMonth.findElement(By.xpath("//a[@data-text='" + pMonth + "']")).click();
        yearOfBirthday.click();
        dropdownListYear.findElement(By.xpath("//a[@data-text='" + pYear + "']")).click();
    }

    public void setGender(final String pGender) {
        if (pGender.equalsIgnoreCase("Male")) {
            radiobuttonMaleGender.click();
        } else {
            radiobuttonFemaleGender.click();
        }
    }

    public void setDesiredMailbox(final String pMailbox, final boolean pIsTimeoutEnabled) {
        typeValue(desiredMailboxField, pMailbox);
        if (pIsTimeoutEnabled) {
            wait.until(ExpectedConditions.visibilityOf(vacantEmailMessage));
        }
    }

    public void setDesiredMailbox(final String pMailbox, final String pMailDomain, final boolean pIsTimeoutEnabled) {
        typeValue(desiredMailboxField, pMailbox);
        desiredMailboxDomain.click();
        dropdownListMailboxDomain.findElement(By.xpath("//a[@data-text='" + pMailDomain + "']")).click();
        if (pIsTimeoutEnabled) {
            try {
                wait.until(ExpectedConditions.visibilityOf(vacantEmailMessage));
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public String selectVacantMailbox() {
        if (vacantEmailsTooltip.isDisplayed()) {
            final Random random = new Random();
            List<WebElement> mVacantEmails = new ArrayList<>();
            mVacantEmails = vacantEmailsTooltip.findElements(By.xpath("//div[contains(@class,'b-list__item__content')]"));
            mSelectedEmail = vacantEmailsTooltip.findElement(By.xpath("//div[@data-index=" + random.nextInt((mVacantEmails.size())) + "]"));
            String selectedEmail = mSelectedEmail.getText();
            mSelectedEmail.click();
            return selectedEmail;
        } else return "VacantEmailsTooltip is not displayed";
    }

    public void setPassword(final String pPassword) {
        typeValue(passwordField, pPassword);
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//span[contains (@class, 'b-password__status-text_')]"), "Укажите пароль")));
    }

    public void setPasswordVisibility(final boolean pIsVisible) {
        if (pIsVisible != iconPasswordEye.getAttribute("Class").contains("b-password__eye_active")) {
            iconPasswordEye.click();
        }
    }

    public void setPasswordConfirmation(final String pPassword) {
        typeValue(passwordConfirmationField, pPassword);
    }

    public void setPhoneNumber(final String pPhoneNumber) {
        typeValue(phoneField, pPhoneNumber);
        try {
            wait.until(ExpectedConditions.visibilityOf(phoneMessage));
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void setAdditionalEmail (final String pAdditionalEmail){
        typeValue(additionalEmailField,pAdditionalEmail);
    }

    public void registerUser() {
        registerButton.submit();
    }

    public void executeUsersRegistration(final int rowNum) throws Exception {
        mDriver.manage().timeouts().pageLoadTimeout(10, SECONDS);
        String mFirstName = ExcelUtil.getCellData(rowNum, 0);
        String mLastName = ExcelUtil.getCellData(rowNum, 1);
        String mDayOfBD = ExcelUtil.getCellData(rowNum, 2);
        String mMonthOfBD = ExcelUtil.getCellData(rowNum, 3);
        String mYearOfBD = ExcelUtil.getCellData(rowNum, 4);
        String mGender = ExcelUtil.getCellData(rowNum, 5);
        String mDesiredLogin = ExcelUtil.getCellData(rowNum, 6);
        String mDesiredDomain = ExcelUtil.getCellData(rowNum, 7);
        String mPassword = ExcelUtil.getCellData(rowNum, 8);
        String mRepassword = ExcelUtil.getCellData(rowNum, 9);
        String mPhoneNumber = ExcelUtil.getCellData(rowNum, 10);
        String mAdditionalEmail = ExcelUtil.getCellData(rowNum, 11);
        setFirstName(mFirstName);
        setLastName(mLastName);
        setDateOfBirthday(mDayOfBD, mMonthOfBD, mYearOfBD);
        setGender(mGender);
        setDesiredMailbox(mDesiredLogin,mDesiredDomain, true);
        if (getVacantEmailMessage().getText().contains("Ящик с таким именем уже существует.")){
            selectVacantMailbox();
        }
        setPassword(mPassword);
        setPasswordConfirmation(mRepassword);
        if(!mPhoneNumber.isEmpty()){
            setPhoneNumber(mPhoneNumber);
        }
        if(!mAdditionalEmail.isEmpty()){
            linkNoPhone.click();
            setAdditionalEmail(mAdditionalEmail);
        }
        registerUser();

        // mDriver.manage().timeouts().pageLoadTimeout(10, SECONDS);

    }

    public void makeNotActive() {
        headerMain.click();
    }

    public WebElement getFirstNameField() {
        return firstNameField;
    }

    public WebElement getFirstNameError() {
        return firstNameError;
    }

    public WebElement getLastNameField() {
        return lastNameField;
    }

    public WebElement getLastNameError() {
        return lastNameError;
    }

    public WebElement getSelectedDayOfBirthday() {
        return selectedDayOfBirthday;
    }

    public WebElement getSelectedMonthOfBirthday() {
        return selectedMonthOfBirthday;
    }

    public WebElement getSelectedYearOfBirthday() {
        return selectedYearOfBirthday;
    }

    public WebElement getBirthdayErrorMessage() {
        return birthdayErrorMessage;
    }

    public WebElement getGenderErrorMessage() {
        return genderErrorMessage;
    }

    public WebElement getDesiredMailboxField() {
        return desiredMailboxField;
    }

    public WebElement getTextMailboxDomain() {
        return textMailboxDomain;
    }

    public WebElement getMailboxErrorMessage() {
        return mailboxErrorMessage;
    }

    public WebElement getVacantEmailMessage() {
        return vacantEmailMessage;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getPasswordTooltip() {
        return passwordTooltip;
    }

    public WebElement getProgressbarPasswordStatus() {
        return progressbarPasswordStatus;
    }

    public WebElement getTextPasswordStatus() {
        return textPasswordStatus;
    }

    public WebElement getTextStatusErrorReason() {
        return textStatusErrorReason;
    }

    public WebElement getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public WebElement getHeaderRegistration() {
        return headerRegistration;
    }

    public WebElement getIconPasswordEye() {
        return iconPasswordEye;
    }

    public WebElement getHintAboutIncorrectRepassword() {
        return hintAboutIncorrectRepassword;
    }

    public WebElement getPhoneField() {
        return phoneField;
    }

    public WebElement getPhoneMessage() {
        return phoneMessage;
    }

    public WebElement getHintPhoneError() {
        return hintPhoneError;
    }


}
