package com.miro.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage extends BasePage {
    private static final String BASE_URL = "https://miro.com/";

    @FindBy(xpath = "//a[@href='/login/']")
    private WebElement buttonLogin;

    @FindBy(xpath = "//input[@data-testid='mr-form-login-email-1']")
    private WebElement inputLogin;

    public WelcomePage openPage() {
        webDriver.get(BASE_URL);
        logger.info("Open welcome page");
        waitForVisibilityOfElement(buttonLogin);
        return this;
    }

    public LoginPage clickLogin() {
        buttonLogin.click();
        logger.info("click to log in");
        logger.info("Open login page");
        waitForVisibilityOfElement(inputLogin);
        return new LoginPage();
    }
}