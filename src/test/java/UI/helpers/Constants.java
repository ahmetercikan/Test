package UI.helpers;

import org.openqa.selenium.By;
import UI.helpers.BrowserUtils;

import static UI.helpers.BrowserUtils.*;

public class Constants {


    //Login
    public static String url = System.getProperty("url");
    public static String emailValue = System.getProperty("email");
    public static String passwordValue = System.getProperty("password");
    public static By email = By.id("login-email");
    public static By password = By.id("login-password-input");
    public static By loginButton = By.xpath("//form/button");
    public static By errorBox = By.id("error-box-wrapper");
    public static By userInfoBtn = By.xpath("//*[contains(text(),'Hesabım')]");
}
