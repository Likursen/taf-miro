package com.miro.ui.tests;

import com.miro.ui.domain.Board;
import com.miro.ui.pages.HomePage;
import com.miro.ui.steps.Steps;
import com.miro.utils.Utils;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Story(value = "Home page checks")
public class HomePageTest extends BaseTest {

    @Test(description = "Board deleting test", dependsOnMethods = "createBoardTest")
    public void deleteBoardTest() {
        Steps steps = new Steps();
        steps.authorization(LOGIN, PASSWORD);
        HomePage homePage = new HomePage();
        Board copyOfDeletedBoard = homePage.getFirstBoard();
        homePage.deleteBoard(copyOfDeletedBoard);
        boolean isDeletedBoardPresent = homePage.findBoardByName(copyOfDeletedBoard);
        Assert.assertFalse(isDeletedBoardPresent, "deleted board present");
    }

    @Test(description = "Board creating test")
    public void createBoardTest() {
        Steps steps = new Steps();
        String boardTitle = Utils.generateRandomSting();
        String boardDescription = Utils.generateRandomSting();
        Board expectedBoard = new Board(boardTitle, boardDescription);
        HomePage homePage = steps.authorization(LOGIN, PASSWORD)
                .createBoardAndReturnToHomePage(expectedBoard);
        boolean isCreatedBoardPresent = homePage.findBoardByName(expectedBoard);
        Assert.assertTrue(isCreatedBoardPresent, "Created board absent");
        Board actualBoard = homePage.openBoardDetail(expectedBoard)
                .generateBoard();
        Assert.assertEquals(actualBoard, expectedBoard, "Expected and actual board (name and description) not equals");
    }
}