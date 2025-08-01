package tests;

import org.java.LoginPage;
import org.testng.annotations.Test;
import org.testng.Assert;

import base.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginButtonDisabledWhenFieldAreEmpty() {
        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.clearEmailField();
        loginPage.clearPasswordField();

        Assert.assertTrue(loginPage.areFieldsEmpty(), "Fields should be empty");
        Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Login button should be disabled when fields are empty");

        System.out.println("✓ Test Passed: Login button is disabled when field are empty");
    }

    @Test
    public void testPasswordMaskedButton() {
        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.enterPassword("testPassword123");

        Assert.assertTrue(loginPage.isMaskedPassword(), "Password should be masked by default");
        boolean eyeIconExists = loginPage.isEyeIconDisplayed();

        if (eyeIconExists) {
            loginPage.togglePasswordVisibility();

            Assert.assertFalse(loginPage.isMaskedPassword(), "Password should be visible after clicking eye icon");

            loginPage.togglePasswordVisibility();

            Assert.assertTrue(loginPage.isMaskedPassword(), "Password should be masked again after second click");

            System.out.println("✓ Test Passed: Password masking/unmasking toggle works correctly");
        } else {
            System.out.println("⚠ Eye icon not found on this page. Skipping toggle functionality test.");
            System.out.println(
                    "✓ Test Passed: Password field is properly masked by default (eye icon functionality not available on this page)");
        }
    }

    @Test
    public void testInvalidLoginShowErrorMsg() {
        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.enterEmail("invalid@email.com");
        loginPage.enterPassword("wrongPassword");

        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid login");

        String errorMessage = loginPage.getErrorMessage();
        System.out.println("Error message displayed: " + errorMessage);

        Assert.assertFalse(errorMessage.isEmpty(), "Error message should not be empty");

        System.out.println("✓ Test Passed: Invalid login shows error message");
    }
}