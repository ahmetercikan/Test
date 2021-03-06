package UI.testCases;

import UI.helpers.Excel;
import UI.pages.BaseClass;
import UI.pages.LoginPage;
import org.junit.experimental.categories.Category;
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


public class LoginPageTest extends BaseClass {

    static LoginPage loginPage = new LoginPage();

    public static void login(String userName, String passWord,String testName,String description) throws InterruptedException, MalformedURLException
    {
        launchBrowser(url+"/login");
        logger = extent.startTest(testName, description);
        loginPage.fillUpEmailandPassword(userName, passWord);
        loginPage.submitButton();
    }
    @Test(description = "Başarılı Kullanıcı Girişi",groups = "MyGroup",priority = 2)
    public void loginSucces() throws InterruptedException, MalformedURLException {
        login(emailValue,passwordValue,"Başarılı Kullanıcı Girişi","Başarılı Kullanıcı Girişi");
        String userInfo = fluentWait(userInfoBtn,driver).getText();
        if (userInfo.equals("Hesabım") || userInfo.equals("Favorilerim") || userInfo.equals("Sepetim"))
        {System.out.println("Giriş işlemi Başarılı");
        }else {Assert.fail("Giriş Başarısız");}
    }
    @Test(description = "Hatalı Email ile Giriş",groups = "MyGroup",priority = 3)
    public void wrongEmail() throws InterruptedException, MalformedURLException {
        login("hatali@mail", "password","Hatalı Email ile Giriş", "Hatalı Email ile Giriş");
        assertControl((errorBox),"HATALI CASE");
    }
    @Test(description = "Hatalı Şifre ile Giriş",priority = 4)
    public void wrongPassword() throws InterruptedException, MalformedURLException {
        login(emailValue, "password","Hatalı Şifre ile Giriş", "Hatalı Şifre ile Giriş");
        assertControl((errorBox),"Lütfen geçerli bir e-posta adresi giriniz.");
    }
    @Test(description = "Boş Email ile Giriş",priority = 5)
    public void blankEmail() throws InterruptedException, MalformedURLException {
        login("", "password","Boş Email ile Giriş", "Boş Email ile Giriş");
        assertControl((errorBox),"Lütfen geçerli bir e-posta adresi giriniz.");
    }
    @Test(description = "Boş Password ile Giriş",priority = 6)
    public void blankPassword() throws InterruptedException, MalformedURLException {
        login("test@mail", "","Boş Password ile Giriş", "Boş Password ile Giriş");
        assertControl((errorBox),"Lütfen şifrenizi giriniz.");
    }
    @Test(description = "Boş Email ve Password ile Giriş",priority = 7)
    public void blankEmailandPassword() throws InterruptedException, MalformedURLException {
        login("", "","Boş Email ve Password ile Giriş", "Boş Email ve Password ile Giriş");
        assertControl((errorBox),"Lütfen geçerli bir e-posta adresi giriniz.");
    }
}
