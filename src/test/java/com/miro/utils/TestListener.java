package com.miro.utils;


import com.miro.ui.webDriver.WebDriverSingleton;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    private final Logger Logger = LogManager.getLogger();

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod()
                .getConstructorOrMethod()
                .getName();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Logger.info("Method start: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Logger.info("Method success: " + getTestMethodName(iTestResult) + "\n");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Logger.error("Method failed: " + getTestMethodName(iTestResult) + "\n");
        WebDriver driver = WebDriverSingleton.getWebDriver();
        if (driver != null) {
            saveScreenshot();
            Logger.info("Screenshot taken");
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Logger.warn("Method skipped: " + getTestMethodName(iTestResult) + "\n");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Logger.error("Method failed within success percentage: " + getTestMethodName(iTestResult) + "\n");
        WebDriver driver = WebDriverSingleton.getWebDriver();
        if (driver != null) {
            saveScreenshot();
            Logger.info("Screenshot taken");
        }
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Logger.info("Tests start: " + iTestContext.getName() + "\n");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Logger.info("Tests finish: " + iTestContext.getName() + "\n");
    }

    private void saveScreenshot() {
        File screenCapture = ((TakesScreenshot) WebDriverSingleton
                .getWebDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                            + getCurrentTimeAsString() +
                            ".png"));
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenCapture));
        } catch (IOException e) {
            Logger.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}