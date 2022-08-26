package com.miro.ui.pages;


import com.miro.ui.webDriver.WebDriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected static final Logger logger = LogManager.getLogger();
    protected static final Duration WAIT_TIMEOUT_SECONDS = Duration.ofSeconds(10);
    protected static final CharSequence[] CHAR_SEQUENCES_DELETE_ALL = {Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE};
    protected WebDriver webDriver = WebDriverSingleton.getWebDriver();

    public BasePage() {
        PageFactory.initElements(webDriver, this);
    }

    protected BasePage(SearchContext context) {
        PageFactory.initElements(new DefaultElementLocatorFactory(context), this);
    }

    protected void clearAndTypeToField(WebElement field, String text) {
        field.sendKeys(CHAR_SEQUENCES_DELETE_ALL);
        field.sendKeys(text);
    }

    protected void waitForElementToBeClickable(WebElement webElement) {
        new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitForVisibilityOfElement(WebElement webElement) {
        new WebDriverWait(webDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void mouseOverAndClick(WebElement webElement) {
        Actions builder = new Actions(webDriver);
        builder.moveToElement(webElement)
                .perform();
        waitForElementToBeClickable(webElement);
        webElement.click();
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    //todo obrabotat
    protected void closePopUp(WebElement buttonPopUpClose) {
        try {
            buttonPopUpClose.click();
        } catch (NoSuchElementException exception) {
            logger.warn("Pop up not found");
        }
    }

    protected void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}