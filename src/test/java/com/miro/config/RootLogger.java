package com.miro.config;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RootLogger {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static Logger getRootLogger() {
        return LOGGER;
    }
}