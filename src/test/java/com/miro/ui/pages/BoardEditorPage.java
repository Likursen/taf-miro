package com.miro.ui.pages;

import com.miro.ui.popUp.BoardRecommendationPopUp;
import com.miro.ui.popUp.EditBoardSettingsPopUp;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardEditorPage extends BasePage {

    @FindBy(xpath = "//div[@data-testid='board-header__title']")
    private WebElement boardTitle;

    @FindBy(xpath = "//div[@data-testid='board-header__logo']")
    private WebElement logo;

    @FindBy(xpath = "//boards-grid-view")
    private WebElement boardsGrid;

    @FindBy(xpath = "//input[@data-testid='board-info-modal-title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//div[@data-testid='template-picker__container']")
    private WebElement recommendationPopUp;

    public BoardEditorPage() {
        logger.info("Open board editor page");
    }

    public BoardEditorPage closeRecommendationPopUp() {
        getBoardRecommendationPopUp().closePopUp();
        waitForInvisibilityOfElement(recommendationPopUp);
        return this;
    }

    public EditBoardSettingsPopUp clickBoardTitle() {
        boardTitle.click();
        logger.info("Open board settings");
        waitForElementToBeClickable(inputTitle);
        return new EditBoardSettingsPopUp();
    }

    public HomePage clickLogo() {
        logo.click();
        logger.info("Clock logo");
        waitForVisibilityOfElement(boardsGrid);
        return new HomePage();
    }

    private BoardRecommendationPopUp getBoardRecommendationPopUp() {
        waitForVisibilityOfElement(recommendationPopUp);
        return new BoardRecommendationPopUp();
    }
}