package com.miro.ui;

import com.miro.ui.webDriver.WebDriverSingleton;
import com.miro.utils.Credentials;
import com.miro.utils.RootLogger;
import com.miro.utils.TestListener;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseTest {
    protected static final Logger LOGGER = RootLogger.getRootLogger();
    protected static final String LOGIN = Credentials.getLogin();
    protected static final String PASSWORD = Credentials.getPassword();

    @AfterMethod
    public void afterTest() {
        WebDriverSingleton.driverQuit();
        LOGGER.info("WebDriver close");
    }
}