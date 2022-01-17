package UI.testCases;

import UI.helpers.Excel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import UI.pages.BaseClass;
import static API.Services.getResponseCode;
import static UI.helpers.BrowserUtils.ScrollElement;
import static UI.helpers.BrowserUtils.driver;
import static UI.helpers.Constants.url;
import static UI.utils.Browser.clickToBy;
import static UI.utils.Browser.launchBrowser;

public class HomePageTest extends BaseClass
{

    @Test(description = "Butik Link Search")
    public void butikLinkAddCSV() throws Exception {
        launchBrowser(url);
        logger = extent.startTest("butik link", "butik link");
        if (driver.findElement( By.id("Rating-Review") ).isDisplayed())
        {
            clickToBy(By.id("Rating-Review"));
        }
        ScrollElement();
        List<WebElement> butiks = driver.findElements(By.xpath("//a[contains(@href, 'butik')]"));
        System.out.println("Toplam Butik Link : " + butiks.size());
        List<String> responseCode = new ArrayList<>();
        for(WebElement eachButik : butiks){
            String URL=eachButik.getAttribute("href" );
            responseCode.add(getResponseCode("",URL));
        }
        Excel.excelGenerator(responseCode);
    }
}
