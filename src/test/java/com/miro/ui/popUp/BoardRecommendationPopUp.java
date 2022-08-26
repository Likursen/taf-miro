package com.miro.ui.popUp;

import com.miro.ui.pages.BasePage;
import com.miro.ui.pages.BoardEditorPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardRecommendationPopUp extends BasePage {
    @FindBy(xpath = "//button[@data-testid='template-picker__close-button']")
    private WebElement buttonPopUpClose;

    public BoardEditorPage closePopUp() {
        closePopUp(buttonPopUpClose);
        logger.info("Close recommendation");
        return new BoardEditorPage();
    }
}