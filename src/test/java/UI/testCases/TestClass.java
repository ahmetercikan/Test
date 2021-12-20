package UI.testCases;

import UI.helpers.Excel;
import UI.pages.BaseClass;
import UI.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static UI.utils.Browser.*;
import static UI.helpers.BrowserUtils.assertControl;
import static UI.helpers.Constants.*;
import static API.Services.*;


public class TestClass extends BaseClass {

    static LoginPage loginPage = new LoginPage();

    Excel excel = new Excel();

    public static void login(String userName, String passWord,String testName,String description) throws InterruptedException, MalformedURLException
    {
        launchBrowser(url+"/login","Chrome");
        logger = extent.startTest(testName, description);
        loginPage.fillUpEmailandPassword(userName, passWord);
        loginPage.submitButton();
    }
    @Test(description = "Başarılı Kullanıcı Girişi")
    public void loginSucces() throws InterruptedException, MalformedURLException {
        login(emailValue,passwordValue,"Başarılı Kullanıcı Girişi","Başarılı Kullanıcı Girişi");
        String userInfo = fluentWait(userInfoBtn,driver).getText();
        if (userInfo.equals("Hesabım") || userInfo.equals("Favorilerim") || userInfo.equals("Sepetim"))
        {System.out.println("Giriş işlemi Başarılı");
        }else {Assert.fail("Giriş Başarısız");}
    }
    @Test(description = "Hatalı Email ile Giriş")
    public void wrongEmail() throws InterruptedException, MalformedURLException {
        login("hatali@mail", "password","Hatalı Email ile Giriş", "Hatalı Email ile Giriş");
        assertControl((errorBox),"HATALI CASE");
    }
    @Test(description = "Hatalı Şifre ile Giriş")
    public void wrongPassword() throws InterruptedException, MalformedURLException {
        login("test@mail", "password","Hatalı Şifre ile Giriş", "Hatalı Şifre ile Giriş");
        assertControl((errorBox),"E-posta adresiniz ve/veya şifreniz hatalı.");
    }
    @Test(description = "Boş Email ile Giriş")
    public void blankEmail() throws InterruptedException, MalformedURLException {
        login("", "password","Boş Email ile Giriş", "Boş Email ile Giriş");
        assertControl((errorBox),"Lütfen geçerli bir e-posta adresi giriniz.");
    }
    @Test(description = "Boş Password ile Giriş")
    public void blankPassword() throws InterruptedException, MalformedURLException {
        login("test@mail", "","Boş Password ile Giriş", "Boş Password ile Giriş");
        assertControl((errorBox),"Lütfen şifrenizi giriniz.");
    }
    @Test(description = "Boş Email ve Password ile Giriş")
    public void blankEmailandPassword() throws InterruptedException, MalformedURLException {
        login("", "","Boş Email ve Password ile Giriş", "Boş Email ve Password ile Giriş");
        assertControl((errorBox),"Lütfen geçerli bir e-posta adresi giriniz.");
    }
    @Test(description = "Butik Link Search")
    public void butikLinkAddCSV() throws Exception {
        launchBrowser(url,"Chrome");
        logger = extent.startTest("butik link", "butik link");
        clickToBy(By.id("Rating-Review"));
        ScrollElement();
        List<WebElement> butiks = driver.findElements(By.xpath("//a[contains(@href, 'butik')]"));
        System.out.println("Toplam Butik Link : " + butiks.size());
        List<String[]> responseCode = new ArrayList<>();
        for(WebElement eachButik : butiks){
            String URL=eachButik.getAttribute("href" );
            responseCode.add(getResponseCode("",URL));
    }
        excel.excelGenerator(responseCode);
    }
}
