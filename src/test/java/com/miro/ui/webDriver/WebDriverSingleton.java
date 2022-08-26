package com.miro.ui.webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebDriverSingleton {
    protected static final Duration WAIT_TIMEOUT_SECONDS = Duration.ofSeconds(10);
    private static WebDriver webDriver;
    private static ChromeOptions options;

    private WebDriverSingleton() {
    }

    public static WebDriver getWebDriver() {
        options = new ChromeOptions()
                .addArguments("--lang=en");
        if (webDriver == null) {
            WebDriverManager.chromedriver()
                    .setup();
            webDriver = new ChromeDriver(options);
            webDriver.manage()
                    .window()
                    .maximize();
            webDriver.manage()
                    .timeouts()
                    .implicitlyWait(WAIT_TIMEOUT_SECONDS);
        }
        return webDriver;
    }

    public static void driverQuit() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}