package UI.utils;

import UI.helpers.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class Browser extends BrowserUtils {

    @BeforeTest
    @Parameters("browser")
    public static void launchBrowser(String url,String browserName) throws MalformedURLException {


        if (browserName.equalsIgnoreCase("Firefox"))
        {
            ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "firefox");
            driver = new RemoteWebDriver(new URL("127.0.0....."),capabilities);
        }
        else if (browserName.equalsIgnoreCase("Chrome")) {
            ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");
            driver = new RemoteWebDriver(new URL("127.0.0....."),capabilities);
        }
        driver.manage().deleteAllCookies();
        driver.get(url);
        driver.manage().window().maximize();
    }
    public static void closeBrowser()
    {
        driver.quit();
    }
    public static void clickToBy(By by) throws InterruptedException {
        try {
            clickTo(visibilityWait(1, by));
        } catch (StaleElementReferenceException sere) {
            Thread.sleep(2000);
            clickToBy(by);
        }
    }
    public static void clickTo(WebElement element) {
        waitForEnableOf(1, element);
        waitForClickableOf(1, element);
        element.click();
    }
}
