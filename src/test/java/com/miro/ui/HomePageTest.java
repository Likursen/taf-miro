package com.miro.ui;

import com.miro.ui.domain.Board;
import com.miro.ui.pages.BoardEditorPage;
import com.miro.ui.pages.HomePage;
import com.miro.ui.pages.WelcomePage;
import com.miro.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void deleteBoardTest() {
        String email = "likursen@gmail.com";
        String password = "dimak124578";
        new WelcomePage()
                .openPage()
                .clickLogin()
                .typeLogin(email)
                .typePassword(password)
                .clickSignIn();
        HomePage homePage = new HomePage().closeNewsPopUp();
        Board copyOfDeletedBoard = homePage.getFirstBoard();
        homePage.deleteBoard(copyOfDeletedBoard);
        boolean isNotificationDeleteDisplayed = homePage.isNotificationDeleteDisplayed();
        boolean isDeletedBoardPresent = homePage.findBoardByName(copyOfDeletedBoard);
        Assert.assertTrue(isNotificationDeleteDisplayed);
        Assert.assertFalse(isDeletedBoardPresent);
    }

    @Test
    public void createBoardTest() {
        String email = "likursen@gmail.com";
        String password = "dimak124578";
        String boardTitle = Utils.generateRandomSting();
        String boardDescription = Utils.generateRandomSting();
        Board expectedBoard = new Board(boardTitle, boardDescription);
        new WelcomePage()
                .openPage()
                .clickLogin()
                .typeLogin(email)
                .typePassword(password)
                .clickSignIn();
        BoardEditorPage boardEditorPage = new HomePage()
                .closeNewsPopUp()
                .clickCreateBoard()
                .closePopUp()
                .fillBoardTitleAndDescription(expectedBoard.getTitle(), expectedBoard.getDescription());
        boolean isNotificationSaveDisplayed = boardEditorPage.isNotificationSaveDisplayed();
        HomePage homePage = boardEditorPage.clickLogo();
        boolean isCreatedBoardPresent = homePage.findBoardByName(expectedBoard);
        Board actualBoard = homePage.openBoardDetail(expectedBoard)
                .generateBoard();
        Assert.assertTrue(isNotificationSaveDisplayed);
        Assert.assertTrue(isCreatedBoardPresent);
        Assert.assertEquals(actualBoard, expectedBoard);
    }
}