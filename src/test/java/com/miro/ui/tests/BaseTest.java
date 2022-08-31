package com.miro.ui.tests;

import com.miro.config.RootLogger;
import com.miro.config.TestListener;
import com.miro.config.WebDriverSingleton;
import com.miro.utils.PropertiesLoader;
import com.miro.utils.Property;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseTest {
    private static final Property properties = PropertiesLoader.getProperties();
    protected static final Logger LOGGER = RootLogger.getRootLogger();
    protected static final String LOGIN = properties.login();
    protected static final String PASSWORD = properties.password();

    @AfterMethod
    public void afterTest() {
        WebDriverSingleton.driverQuit();
        LOGGER.info("WebDriver close");
    }
}