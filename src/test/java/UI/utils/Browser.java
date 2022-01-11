package UI.utils;

import UI.helpers.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Browser extends BrowserUtils {

    static final String HOST_URL = "http://localhost:4444/wd/hub";
    //http://192.168.0.150:4444/wd/hub

    @BeforeTest
    @Parameters(value = {"browserName","browserVersion","platformName"})
    public static void launchBrowser(String url){
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("build", "ParallelTestNG");
        cap.setCapability("name", "ParallelTestNG");
        cap.setCapability("network", true); // To enable network logs
        cap.setCapability("visual", true); // To enable step by step screenshot
        cap.setCapability("video", true); // To enable video recording
        cap.setCapability("console", true); // To capture console logs
        try {
            driver = new RemoteWebDriver(new URL(HOST_URL), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
    }
    public static void closeBrowser()
    {
        if (driver != null) {
            driver.quit();
        }
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
