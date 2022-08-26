package com.miro.ui.popUp;

import com.miro.ui.pages.BasePage;
import com.miro.ui.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteConfirmPopUp extends BasePage {

    @FindBy(xpath = "//button[@data-testid='confirmation-modal__submit-button']")
    private WebElement buttonSubmitDelete;

    public HomePage clickSubmitDelete() {
        mouseOverAndClick(buttonSubmitDelete);
        //todo comment
        sleep(1000);
        logger.info("Click delete submit");
        return new HomePage();
    }
}