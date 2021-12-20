package UI.pages;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;

import static UI.helpers.BrowserUtils.driver;
import static UI.utils.Browser.closeBrowser;

public class BaseClass {

    public static ExtentReports extent;
    public static ExtentTest logger;
    public static String odevApiURL = "ApiURL";

    @BeforeTest
    public void startReport()
    {
        extent = new ExtentReports(
                System.getProperty("user.dir") + "/test-output/Odev.html", true);
        extent.addSystemInfo("Tool","TestNG").addSystemInfo("Project", "Odev")
                .addSystemInfo("Environment", "Test Ortam")
                .addSystemInfo("User Name", "Ahmet Mesut Ercikan");
        extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());

            String screenShot = System.getProperty("user.dir")+"\\ScreenShot\\FileName.png";
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(screenShot));

        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
        }
        if(result.getStatus()==ITestResult.FAILURE) {
            String reason = result.getThrowable().toString();
            logger.log(LogStatus.FAIL, reason);
            extent.endTest(logger);
        }
        extent.flush();
        closeBrowser();
    }
}
