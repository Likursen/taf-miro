package com.miro.ui.tests;

import com.miro.ui.pages.HomePage;
import com.miro.ui.pages.LoginPage;
import com.miro.ui.pages.WelcomePage;
import com.miro.ui.steps.Steps;
import com.miro.utils.Utils;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Story("Login page checks")
public class LoginPageTest extends BaseTest {

    @Test(description = "test login with empty credential")
    public void testLoginWithEmptyCredentials() {
        String expectedEmailErrorText = "Enter your email address.";
        String expectedPasswordErrorText = "Enter your password.";
        LoginPage loginPage = new WelcomePage()
                .openPage()
                .clickLogin()
                .clickSignIn();
        String actualEmailErrorText = loginPage.getEmailErrorText();
        Assert.assertEquals(actualEmailErrorText, expectedEmailErrorText, "Actual and exist email error not equals");
        String actualPasswordErrorText = loginPage.getPasswordErrorText();
        LOGGER.info("Expected email error: " + expectedEmailErrorText);
        LOGGER.info("Expected password error: " + expectedPasswordErrorText);
        Assert.assertEquals(actualPasswordErrorText, expectedPasswordErrorText, "Actual and exist password error not equals");
    }

    @Test(description = "test login with invalid credential")
    public void testLoginWithInvalidCredential() {
        String expectedLoginError = "Enter a valid email address and password.";
        String email = Utils.generateRandomEmail();
        String password = Utils.generateRandomSting();
        new Steps().authorization(email, password);
        String actualLoginErrorText = new LoginPage().getLoginErrorText();
        LOGGER.info("Expected login error: " + actualLoginErrorText);
        Assert.assertEquals(actualLoginErrorText, expectedLoginError, "Actual and exist login error not equals");
    }

    @Test(description = "test login with valid credential")
    public void testLoginWitValidCredential() {
        new Steps().authorization(LOGIN, PASSWORD);
        String actualUserEmailText = new HomePage()
                .clickButtonUserProfile()
                .getUserEmailText();
        LOGGER.info("Expected email: " + LOGIN);
        Assert.assertEquals(actualUserEmailText, LOGIN, "Actual and exist email not equals");
    }
}
