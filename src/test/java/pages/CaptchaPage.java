package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CaptchaPage extends Page {

    public CaptchaPage(WebDriver pDriver) {
        super(pDriver);
    }

    @FindBy (xpath="//input[contains(@id,'capcha_')]")
    public WebElement captchaField;
}
