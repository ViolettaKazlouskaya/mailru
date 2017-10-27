package basic;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;

public class BasicTestCase {

    protected WebDriver mDriver;

    public BasicTestCase(String pBrowserType) {
        try {
            if (pBrowserType.equalsIgnoreCase("Firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--disable-notifications");
                mDriver = new FirefoxDriver(options);
            } else if (pBrowserType.equalsIgnoreCase("Chrome")) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe");
                mDriver = new ChromeDriver(chromeOptions);
            } else if (pBrowserType.equalsIgnoreCase("InternetExplorer")) {
                System.setProperty("webdriver.ie.driver", "C:\\Program Files\\IEDriver\\IEDriverServer.exe");
                mDriver = new InternetExplorerDriver();
            }
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
        }
    }

    public void captureScreen(final String pFileName) {
        String path;
        try {
            WebDriver augmentedDriver = new Augmenter().augment(mDriver);
            File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
            path = "c:\\tmp\\" + pFileName;
            FileUtils.copyFile(source, new File(path));
        }
        catch(IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @After
    public void tearDown(){
        mDriver.close();
    }
}
