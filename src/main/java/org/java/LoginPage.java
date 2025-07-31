package org.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    By emailField = By.name("email");
    By passwordField = By.name("password");
    By loginButton = By.className("login-button");
    By eyeIcon = By.className("password-visible");

    public void enterEmail(String email) {
        webDriver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        webDriver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        webDriver.findElement(loginButton).click();
    }

    public void togglePasswordVisibility() {
        webDriver.findElement(eyeIcon).click();
    }

    public boolean isLoginButtonEnabled() {
        return webDriver.findElement(loginButton).isEnabled();
    }

    public boolean isMaskedPassword() {
        WebElement passwordElement = webDriver.findElement(passwordField);
        return passwordElement.getAttribute("type").equals("password");
    }
}