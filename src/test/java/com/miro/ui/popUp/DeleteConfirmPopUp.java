package com.miro.ui.popUp;

import com.miro.ui.pages.BasePage;
import com.miro.ui.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteConfirmPopUp extends BasePage {

    @FindBy(xpath = "//button[@data-testid='confirmation-modal__submit-button']")
    private WebElement buttonSubmitDelete;

    @FindBy(xpath = "//div[@data-testid='dialog-container-content']")
    private WebElement confirmDeletePopUp;

    public HomePage clickSubmitDelete() {
        mouseOverAndClick(buttonSubmitDelete);
        logger.info("Click delete submit");
        waitForInvisibilityOfElement(confirmDeletePopUp);
        return new HomePage();
    }
}