package com.miro.ui.popUp;

import com.miro.ui.pages.BasePage;
import com.miro.ui.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsPopUp extends BasePage {

    @FindBy(xpath = "//button[@data-testid='changes__dialog-close-button']")
    private WebElement buttonPopUpClose;

    public HomePage clickClosePopUp() {
        closePopUp(buttonPopUpClose);
        logger.info("Close news");
        return new HomePage();
    }
}