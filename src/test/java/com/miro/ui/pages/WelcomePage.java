package com.miro.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage extends BasePage {
    private static final String BASE_URL = "https://miro.com/";

    @FindBy(xpath = "//a[@href='/login/']")
    private WebElement buttonLogin;

    public WelcomePage openPage() {
        webDriver.get(BASE_URL);
        logger.info("Open welcome page");
        return this;
    }

    public LoginPage clickLogin() {
        buttonLogin.click();
        logger.info("click to log in");
        return new LoginPage();
    }
}