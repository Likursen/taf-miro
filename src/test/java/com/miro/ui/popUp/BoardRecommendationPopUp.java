package com.miro.ui.popUp;

import com.miro.ui.pages.BasePage;
import com.miro.ui.pages.BoardEditorPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardRecommendationPopUp extends BasePage {
    @FindBy(xpath = "//button[@data-testid='template-picker__close-button']")
    private WebElement buttonPopUpClose;

    @FindBy(xpath = "//div[@data-testid='board-header__title']")
    private WebElement boardTitle;

    public BoardEditorPage closePopUp() {
        buttonPopUpClose.click();
        logger.info("Close recommendation");
        waitForElementToBeClickable(boardTitle);
        return new BoardEditorPage();
    }
}