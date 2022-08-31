package com.miro.config;

import com.miro.config.annotation.TestAPI;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
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
    private final Logger logger = Logger.getRootLogger();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info("Method start: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("Method success: " + getTestMethodName(iTestResult) + "\n");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.error("Method failed: " + getTestMethodName(iTestResult) + "\n");
        if (!isTestApiAnnotationPresent(iTestResult)) {
            WebDriver driver = WebDriverSingleton.getWebDriver();
            if (driver != null) {
                saveScreenshot();
                logger.info("Screenshot taken");
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.warn("Method skipped: " + getTestMethodName(iTestResult) + "\n");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.info("Tests start: " + iTestContext.getName() + "\n");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.info("Tests finish: " + iTestContext.getName() + "\n");
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
            logger.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

    private boolean isTestApiAnnotationPresent(ITestResult iTestResult) {
        return iTestResult.getTestClass()
                .getRealClass()
                .isAnnotationPresent(TestAPI.class);
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult
                .getMethod()
                .getConstructorOrMethod()
                .getName();
    }
}