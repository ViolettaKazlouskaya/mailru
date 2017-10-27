package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page {

    protected WebDriver mDriver;

    public Page(WebDriver pDriver) {
        mDriver = pDriver;
    }


    protected void typeValue(final WebElement pWebElement,final String pValue) {
        pWebElement.clear();
        pWebElement.sendKeys(pValue);
    }

}
