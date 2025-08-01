package org.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    By emailField = By.name("email");
    By passwordField = By.name("password");
    By loginButton = By
            .cssSelector("button[type='submit'], .login-button, input[type='submit'], button.btn-primary, .btn-login");
    By eyeIcon = By.cssSelector(
            "button[type='button'] svg, .eye-icon, .show-password, .toggle-password, [data-testid='password-toggle'], .fa-eye, .password-toggle");

    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    public void togglePasswordVisibility() {
        WebElement eyeElement = wait.until(ExpectedConditions.elementToBeClickable(eyeIcon));
        eyeElement.click();
        wait.until(ExpectedConditions.stalenessOf(eyeElement));
    }

    public boolean isLoginButtonEnabled() {
        WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));

        String disabledAttr = loginBtn.getAttribute("disabled");
        String ariaDisabledAttr = loginBtn.getAttribute("aria-disabled");
        String classAttr = loginBtn.getAttribute("class");

        if (disabledAttr != null || "true".equals(ariaDisabledAttr)) {
            return false;
        }

        // Check for disabled class patterns
        if (classAttr != null && (classAttr.contains("disabled") || classAttr.contains("btn-disabled"))) {
            return false;
        }

        if (areFieldsEmpty()) {
            return false;
        }

        return loginBtn.isEnabled();
    }

    public boolean isMaskedPassword() {
        WebElement passwordElement = webDriver.findElement(passwordField);
        String typeAttribute = passwordElement.getAttribute("type");
        return typeAttribute != null && typeAttribute.equals("password");
    }

    public String getEmailFieldValue() {
        return webDriver.findElement(emailField).getAttribute("value");
    }

    public String getPasswordFieldValue() {
        return webDriver.findElement(passwordField).getAttribute("value");
    }

    public boolean isEmailFieldDisplayed() {
        return webDriver.findElement(emailField).isDisplayed();
    }

    public boolean isPasswordFieldDisplayed() {
        return webDriver.findElement(passwordField).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        try {
            return webDriver.findElement(loginButton).isDisplayed();
        } catch (Exception e) {
            System.out.println("Login button not found with current selector: " + e.getMessage());
            return false;
        }
    }

    public boolean isEyeIconDisplayed() {
        try {
            return webDriver.findElement(eyeIcon).isDisplayed();
        } catch (Exception e) {
            System.out.println("Eye icon not found with current selector: " + e.getMessage());
            return false;
        }
    }

    public void clearEmailField() {
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailElement.clear();
    }

    public void clearPasswordField() {
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordElement.clear();
    }

    public String getPageTitle() {
        return webDriver.getTitle();
    }

    public boolean areFieldsEmpty() {
        String emailValue = getEmailFieldValue();
        String passwordValue = getPasswordFieldValue();
        return (emailValue == null || emailValue.isEmpty()) &&
                (passwordValue == null || passwordValue.isEmpty());
    }

    By errorMessage = By.cssSelector(
            ".error-message, .alert-danger, .invalid-feedback, .error, .login-error, .alert-error, .text-danger, .help-block, .field-error, [role='alert'], .message.error");

    public boolean isErrorMessageDisplayed() {
        try {
            Thread.sleep(1000);

            WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));
            boolean isDisplayed = errorElement.isDisplayed();
            System.out.println("Error message found and displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            System.out.println(
                    "Error message not found with current selector, trying alternative approach: " + e.getMessage());

            try {
                java.util.List<WebElement> allElements = webDriver.findElements(By.xpath(
                        "//*[contains(text(), 'error') or contains(text(), 'Error') or contains(text(), 'invalid') or contains(text(), 'Invalid') or contains(text(), 'wrong') or contains(text(), 'Wrong') or contains(text(), 'failed') or contains(text(), 'Failed')]"));
                if (!allElements.isEmpty()) {
                    System.out.println("Found potential error elements: " + allElements.size());
                    for (WebElement elem : allElements) {
                        if (elem.isDisplayed()) {
                            System.out.println("Potential error text: " + elem.getText());
                            return true;
                        }
                    }
                }
            } catch (Exception e2) {
                System.out.println("Alternative error search failed: " + e2.getMessage());
            }

            return false;
        }
    }

    public String getErrorMessage() {
        try {
            WebElement errorElement = webDriver.findElement(errorMessage);
            String message = errorElement.getText();
            System.out.println("Retrieved error message: " + message);
            return message;
        } catch (Exception e) {
            try {
                java.util.List<WebElement> allElements = webDriver.findElements(By.xpath(
                        "//*[contains(text(), 'error') or contains(text(), 'Error') or contains(text(), 'invalid') or contains(text(), 'Invalid') or contains(text(), 'wrong') or contains(text(), 'Wrong') or contains(text(), 'failed') or contains(text(), 'Failed')]"));
                if (!allElements.isEmpty()) {
                    for (WebElement elem : allElements) {
                        if (elem.isDisplayed()) {
                            String text = elem.getText();
                            System.out.println("Found potential error message: " + text);
                            return text;
                        }
                    }
                }
            } catch (Exception e2) {
                System.out.println("Alternative error message search failed: " + e2.getMessage());
            }

            return "";
        }
    }
}