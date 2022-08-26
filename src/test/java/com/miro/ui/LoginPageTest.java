package com.miro.ui;

import com.miro.ui.pages.HomePage;
import com.miro.ui.pages.LoginPage;
import com.miro.ui.pages.WelcomePage;
import com.miro.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void testLoginWithEmptyCredentials() {
        String expectedEmailErrorText = "Enter your email address.";
        String expectedPasswordErrorText = "Enter your password.";

        LoginPage loginPage = new WelcomePage()
                .openPage()
                .clickLogin()
                .clickSignIn();
        String actualEmailErrorText = loginPage.getEmailErrorText();
        String actualPasswordErrorText = loginPage.getPasswordErrorText();

        logger.info("Expected email error: " + expectedEmailErrorText);
        logger.info("Expected password error: " + expectedPasswordErrorText);
        Assert.assertEquals(actualEmailErrorText, expectedEmailErrorText);
        Assert.assertEquals(actualPasswordErrorText, expectedPasswordErrorText);
    }

    @Test
    public void testLoginWithInvalidCredential() {
        String expectedLoginError = "Enter a valid email address and password.";

        String email = Utils.generateRandomEmail();
        String password = Utils.generateRandomSting();
        String actualLoginErrorText = new WelcomePage()
                .openPage()
                .clickLogin()
                .typeLogin(email)
                .typePassword(password)
                .clickSignIn()
                .getLoginErrorText();

        logger.info("Expected login error: " + actualLoginErrorText);
        Assert.assertEquals(actualLoginErrorText, expectedLoginError);
    }

    @Test
    public void testLoginWitValidCredential() {
        String email = "likursen@gmail.com";
        String password = "dimak124578";
        new WelcomePage()
                .openPage()
                .clickLogin()
                .typeLogin(email)
                .typePassword(password)
                .clickSignIn();
        String actualUserEmailText = new HomePage()
                .clickButtonUserProfile()
                .getUserEmailText();

        logger.info("Expected email: " + email);
        Assert.assertEquals(actualUserEmailText, email);
    }
}
