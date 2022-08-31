package com.miro.api.tests;

import com.miro.config.RootLogger;
import com.miro.config.TestListener;
import com.miro.config.annotation.TestAPI;
import com.miro.utils.PropertiesLoader;
import com.miro.utils.Property;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@TestAPI
@Listeners(TestListener.class)
public abstract class BaseTest {
    private static final Property properties = PropertiesLoader.getProperties();
    protected static final Logger LOGGER = RootLogger.getRootLogger();
    protected final static String TOKEN = properties.token();

    @BeforeMethod
    public void beforeMethod() {
        RestAssured.baseURI = "https://api.miro.com";
    }
}