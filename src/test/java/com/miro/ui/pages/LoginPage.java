package com.miro.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@data-testid='mr-form-login-email-1']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@data-testid='mr-form-login-password-1']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@data-testid='mr-form-login-btn-signin-1']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//label[@id='emailError']")
    private WebElement labelEmailError;

    @FindBy(xpath = "//label[@id='passwordError']")
    private WebElement labelPasswordError;

    @FindBy(xpath = "//div[@id='loginError']")
    private WebElement loginError;

    public LoginPage() {
    }

    public LoginPage typeLogin(String login) {
        inputLogin.sendKeys(login);
        logger.info("Type login: " + login);
        return this;
    }

    public LoginPage typePassword(String password) {
        inputPassword.sendKeys(password);
        logger.info("Type password: " + password);
        return this;
    }

    public LoginPage clickSignIn() {
        buttonSignIn.click();
        logger.info("Click Sign In");
        return this;
    }

    public String getEmailErrorText() {
        String text = labelEmailError.getText();
        logger.info("Actual email error text: " + text);
        return text;
    }

    public String getPasswordErrorText() {
        String text = labelPasswordError.getText();
        logger.info("Actual password error text: " + text);
        return text;
    }

    public String getLoginErrorText() {
        String text = loginError.getText();
        logger.info("Actual log in error text: " + text);
        return text;
    }
}