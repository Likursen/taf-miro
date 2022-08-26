package com.miro.api;

import com.miro.utils.Credentials;
import com.miro.utils.RootLogger;
import com.miro.utils.TestListener;
import com.miro.utils.annotation.TestAPI;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@TestAPI
@Listeners(TestListener.class)
public abstract class BaseTest {
    protected static final Logger LOGGER = RootLogger.getRootLogger();
    protected final static String BEARER = Credentials.getToken();

    @BeforeMethod
    public void beforeMethod() {
        RestAssured.baseURI = "https://api.miro.com";
    }
}