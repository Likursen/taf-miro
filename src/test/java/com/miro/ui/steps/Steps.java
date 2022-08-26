package com.miro.ui.steps;

import com.miro.ui.domain.Board;
import com.miro.ui.pages.HomePage;
import com.miro.ui.pages.WelcomePage;

public class Steps {
    public Steps authorization(String login, String password) {
        new WelcomePage()
                .openPage()
                .clickLogin()
                .typeLogin(login)
                .typePassword(password)
                .clickSignIn();
        return this;
    }

    public HomePage createBoardAndReturnToHomePage(Board board) {
        return new HomePage()
                .clickCreateBoard()
                .closeRecommendationPopUp()
                .clickBoardTitle()
                .fillBoardTitleAndDescription(board.getTitle(), board.getDescription())
                .clickLogo();
    }
}
