package com.miro.ui.popUp;

import com.miro.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfilePopUp extends BasePage {

    @FindBy(xpath = "//div[@data-testid='workspace-user-info__email']")
    private WebElement userEmail;

    public String getUserEmailText() {
        String email = userEmail.getText();
        logger.info("Actual user email: " + email);
        return email;
    }
}