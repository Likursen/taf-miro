package com.miro.ui.popUp;

import com.miro.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardContextPopUp extends BasePage {

    @FindBy(xpath = "//div[@data-testid='delete_board_action']")
    private WebElement delete;

    @FindBy(xpath = "//div[@data-testid='details_board_action']")
    private WebElement boardDetails;

    public DeleteConfirmPopUp clickDeleteBoard() {
        mouseOverAndClick(delete);
        logger.info("Click delete board");
        return new DeleteConfirmPopUp();
    }

    public EditBoardSettingsPopUp clickBoardDetail() {
        logger.info("Click board details");
        mouseOverAndClick(boardDetails);
        return new EditBoardSettingsPopUp();
    }
}