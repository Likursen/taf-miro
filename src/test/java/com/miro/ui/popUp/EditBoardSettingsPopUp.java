package com.miro.ui.popUp;

import com.miro.ui.domain.Board;
import com.miro.ui.pages.BasePage;
import com.miro.ui.pages.BoardEditorPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditBoardSettingsPopUp extends BasePage {

    @FindBy(xpath = "//input[@data-testid='board-info-modal-title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@data-testid='board-info-modal-description']")
    private WebElement inputDescription;

    @FindBy(xpath = "//div[@class='md-content']")
    private WebElement editBoardSettingsPopUp;

    public EditBoardSettingsPopUp typeTitle(String title) {
        clearAndTypeToField(inputTitle, title);
        logger.info("Clear and type title: " + title);
        return this;
    }

    public String getTitle() {
        String title = inputTitle.getAttribute("value");
        logger.info("Actual title: " + title);
        return title;
    }

    public EditBoardSettingsPopUp typeDescription(String description) {
        clearAndTypeToField(inputDescription, description);
        logger.info("Clear and type description: " + description);
        return this;
    }

    public String getDescription() {
        String description = inputDescription.getAttribute("value");
        logger.info("Actual description: " + description);
        return description;
    }

    public BoardEditorPage closePopUp() {
        inputTitle.sendKeys(Keys.ENTER);
        logger.info("Close board settings");
        waitForInvisibilityOfElement(editBoardSettingsPopUp);
        return new BoardEditorPage();
    }

    //the wait is necessary because the back-end does not have time to save the changes
    public BoardEditorPage fillBoardTitleAndDescription(String title, String description) {
        typeTitle(title)
                .typeDescription(description);
        sleep(1000);
        closePopUp();
        sleep(1000);
        waitForInvisibilityOfElement(editBoardSettingsPopUp);
        return new BoardEditorPage();
    }

    public Board generateBoard() {
        return new Board()
                .setTitle(getTitle())
                .setDescription(getDescription());
    }
}