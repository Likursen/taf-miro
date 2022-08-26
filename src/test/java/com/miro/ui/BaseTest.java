package com.miro.ui;

import com.miro.ui.webDriver.WebDriverSingleton;
import com.miro.utils.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseTest {
    protected static final Logger logger = LogManager.getLogger();

    @AfterMethod
    public void afterTest() {
        WebDriverSingleton.driverQuit();
        logger.info("WebDriver close");
    }
}