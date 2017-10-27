package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailboxMainPage extends Page {
    public MailboxMainPage(WebDriver pDriver) {
        super(pDriver);
    }

    @FindBy(id = "PH_logoutLink")
    public WebElement logoutLink;

    public void logout () {
        logoutLink.click();
    }

    public WebElement getLogoutLink() {
        return logoutLink;
    }

}
