package UI.helpers;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BrowserUtils {

    public static RemoteWebDriver driver;

    public static WebElement fluentWait(final By locator, RemoteWebDriver driver) {
        Wait<RemoteWebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<RemoteWebDriver>(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<RemoteWebDriver, WebElement>() {
            public WebElement apply(RemoteWebDriver driver) {
                return driver.findElement(locator);
            }
        });

        return foo;
    }
    public static WebElement visibilityWait(int timeoutInSeconds, By by) {
        Wait<WebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<WebDriver>(driver)
                .withTimeout(60, SECONDS)
                .pollingEvery(timeoutInSeconds, SECONDS).
                        ignoring(NotFoundException.class).ignoring(NoSuchElementException.class);
        return wait.until(visibilityOfElementLocated(by));
    }

    public static void waitForEnableOf(int timeOutInSeconds, WebElement elementLocator) {
        Wait<RemoteWebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<RemoteWebDriver>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(timeOutInSeconds, SECONDS).
                        ignoring(NotFoundException.class).ignoring(NoSuchElementException.class);
        wait.until(enableOf(elementLocator));
    }

    public static WebElement waitForClickableOf(int timeOutInSeconds, WebElement elementLocator) {
        Wait<RemoteWebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<RemoteWebDriver>(driver)
                .withTimeout(60, SECONDS)
                .pollingEvery(timeOutInSeconds, SECONDS).
                        ignoring(NotFoundException.class).ignoring(NoSuchElementException.class);
        return wait.until(elementToBeClickable(elementLocator));
    }

    private static ExpectedCondition<Boolean> enableOf(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver ignored) {
                try {
                    element.isEnabled();
                    return true;
                } catch (StaleElementReferenceException sere) {
                    return false;
                }
            }

            public String toString() {
                return String.format("element (%s) to become stale", element);
            }
        };
    }
    public static void assertControl(By by, String expectedMsg)
    {
        String actualMsg = BrowserUtils.visibilityWait(100,by).getText();
        assertThat("Hata : Beklenen Veri Hatalı ",actualMsg,is(expectedMsg));
    }
    public static void ScrollElement(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            long lastHeight=((Number)js.executeScript("return document.body.scrollHeight")).longValue();
            while (true) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(2000);

                long newHeight = ((Number)js.executeScript("return document.body.scrollHeight")).longValue();
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
