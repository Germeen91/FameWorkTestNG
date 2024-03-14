package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CommonMethods;

public class loginPageTest extends CommonMethods {

@Test
    public void loginTest(){
    sendText("Admin", login.usernameField);
    sendText("Hum@nhrm123",login.passwordField);
    click(login.loginButton);

    String actualMessage=dashboardPage.welcomeMessageOption.getText();

    Assert.assertEquals(actualMessage,"Welcome Admin");
}

    @DataProvider(name="cred")
    public Object[][] data (){
        Object[][] loginData = {
                {"Admin", "12345", "Invalid credentials"},
                {"ABCD", "Hum@nhrm123", "Invalid credentials"},
                {"Admin", "", "Password cannot be empty"},
                {"", "Hum@nhrm123", "Username cannot be empty"}
        };
        return loginData;
    }

    @Test(dataProvider = "cred")
    public void loginTest(String username, String pass , String Message){ // to linked our code with the DataProvider
sendText(username, login.usernameField);
sendText(pass, login.passwordField);
click(login.loginButton);
        String actualError = login.errorMessageLoc.getText();

// verify
        Assert.assertEquals(Message,actualError);

    }
}
