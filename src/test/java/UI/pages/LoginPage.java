package UI.pages;

import UI.utils.Browser;
import static UI.helpers.Constants.*;
import static UI.helpers.Constants.password;

public class LoginPage extends Browser {

    public LoginPage submitButton() throws InterruptedException
    {
       clickToBy(loginButton);
       return this;
    }
    public LoginPage fillUpEmailandPassword(String userName, String passWord){
        fluentWait(email,driver).sendKeys(userName);
        fluentWait(password,driver).sendKeys(passWord);
        return this;
    }
}
