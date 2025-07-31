package tests;

import org.java.LoginPage;
import org.testng.annotations.Test;
import org.testng.Assert;

import base.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginButton() {
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertFalse(loginPage.isLoginButtonEnabled());
    }
}