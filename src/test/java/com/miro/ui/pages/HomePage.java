package com.miro.ui.pages;

import com.miro.ui.domain.Board;
import com.miro.ui.popUp.BoardContextPopUp;
import com.miro.ui.popUp.EditBoardSettingsPopUp;
import com.miro.ui.popUp.UserProfilePopUp;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@data-testid='board-brick-item__wrapper']")
    private List<WebElement> itemsBoards;

    @FindBy(xpath = "//button[@data-testid='dashboard__grid__new-board']")
    private WebElement buttonCreateBoard;

    @FindBy(xpath = "//div[@data-testid='user-profile-button__user-icon'][1]")
    private WebElement buttonUserProfile;

    @FindBy(xpath = "//div[@class='user-profile__popup white-popup fade-in-out ng-leave-enter']")
    private WebElement userProfilePopUp;

    @FindBy(xpath = "//button[@data-testid='template-picker__close-button']")
    private WebElement buttonCloseRecommendationPopUp;

    @FindBy(xpath = "//div[@data-testid='details_board_action']")
    private WebElement boardDetails;

    public HomePage() {
        logger.info("Open home page");
    }

    public UserProfilePopUp clickButtonUserProfile() {
        waitForVisibilityOfElement(buttonUserProfile);
        waitForElementToBeClickable(buttonUserProfile);
        buttonUserProfile.click();
        logger.info("Click user profile menu");
        waitForVisibilityOfElement(userProfilePopUp);
        return new UserProfilePopUp();
    }

    public BoardEditorPage clickCreateBoard() {
        waitForElementToBeClickable(buttonCreateBoard);
        waitForVisibilityOfElement(buttonCreateBoard);
        sleep(1000);
        buttonCreateBoard.click();
        logger.info("Click create board");
        waitForElementToBeClickable(buttonCloseRecommendationPopUp);
        return new BoardEditorPage();
    }

    public List<Board> getBoards() {
        return getBoardItems()
                .stream()
                .map(BoardItem::generateBoard)
                .collect(Collectors.toList());
    }

    public boolean findBoardByName(Board board) {
        List<BoardItem> boardItem = getBoardItems();
        if (!boardItem.isEmpty()) {
            boolean isBoardFind = boardItem.stream()
                    .anyMatch(b -> b.getName().equals(board.getTitle()));
            logger.info("Find " + board.getTitle() + ": " + isBoardFind);
            return isBoardFind;
        }
        logger.error("Boards not found");
        return false;
    }

    public Board getFirstBoard() {
        Board board = getBoards()
                .get(0);
        logger.info("First board: " + board.getTitle());
        return board;
    }

    public HomePage deleteBoard(Board board) {
        clickBoardContext(board)
                .clickDeleteBoard()
                .clickSubmitDelete();
        logger.info("Board deleted: " + board.getTitle());
        waitForElementToBeClickable(buttonCreateBoard);
        return this;
    }

    public EditBoardSettingsPopUp openBoardDetail(Board board) {
        clickBoardContext(board)
                .clickBoardDetail();
        logger.info("Open board detail: " + board.getTitle());
        return new EditBoardSettingsPopUp();
    }

    private List<BoardItem> getBoardItems() {
        sleep(2500);
        return itemsBoards.stream()
                .map(BoardItem::new)
                .collect(Collectors.toList());
    }

    private BoardItem getBoardItem(Board board) {
        List<BoardItem> boardItem = getBoardItems();
        if (!boardItem.isEmpty()) {
            return boardItem.stream()
                    .filter(b -> b.getName().equals(board.getTitle()))
                    .collect(Collectors.toList())
                    .get(0);
        }
        logger.error("Boards not found");
        return null;
    }

    private BoardContextPopUp clickBoardContext(Board board) {
        getBoardItem(board)
                .clickContext();
        logger.info("Open board context menu: " + board.getTitle());
        waitForElementToBeClickable(boardDetails);
        return new BoardContextPopUp();
    }

    private static class BoardItem extends BasePage {

        @FindBy(xpath = ".//div[@class='board-brick__title title-QtiTq']")
        private WebElement name;

        @FindBy(xpath = ".//button[@data-testid='dashboard__grid__context-button']")
        private WebElement buttonContext;

        public BoardItem(WebElement board) {
            super(board);
        }

        private Board generateBoard() {
            return new Board().setTitle(getName());
        }

        private String getName() {
            String name = this.name.getText();
            logger.info("Actual board name: " + name);
            return name;
        }

        private BoardContextPopUp clickContext() {
            mouseOverAndClick(buttonContext);
            logger.info("Open board context menu");
            return new BoardContextPopUp();
        }
    }
}