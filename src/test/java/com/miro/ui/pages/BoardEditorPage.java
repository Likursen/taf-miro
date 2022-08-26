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

    @FindBy(xpath = "//span[@class='notification__text notification__text--main']")
    private WebElement notificationSave;

    public BoardEditorPage(){
        logger.info("Open board editor page");
    }

    public BoardEditorPage fillBoardTitleAndDescription(String title, String description) {
        //todo ostavit comment
        EditBoardSettingsPopUp editBoardSettingsPopUp = clickBoardTitle()
                .typeTitle(title)
                .typeDescription(description);
        sleep(1000);
        editBoardSettingsPopUp.closePopUp();
        sleep(1000);
        return this;
    }

    public BoardEditorPage closePopUp() {
        getBoardRecommendationPopUp().closePopUp();
        return this;
    }

    public boolean isNotificationSaveDisplayed() {
        waitForVisibilityOfElement(notificationSave);
        return isElementDisplayed(notificationSave);
    }

    public HomePage clickLogo() {
        logo.click();
        logger.info("Clock logo");
        return new HomePage();
    }

    private BoardRecommendationPopUp getBoardRecommendationPopUp() {
        return new BoardRecommendationPopUp();
    }

    private EditBoardSettingsPopUp clickBoardTitle() {
        waitForVisibilityOfElement(boardTitle);
        boardTitle.click();
        logger.info("Open board settings");
        return new EditBoardSettingsPopUp();
    }
}