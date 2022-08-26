package com.miro.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Credentials {
    private static final File FILE = new File("src/test/resources/credentials.properties");

    public static String getToken() {
        return getFileContent()
                .getProperty("bearer");
    }

    public static String getLogin() {
        return getFileContent()
                .getProperty("login");
    }

    public static String getPassword() {
        return getFileContent()
                .getProperty("password");
    }

    private static Properties getFileContent() {
        Properties property = new Properties();
        FileReader fileReader;
        try {
            fileReader = new FileReader(FILE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            property.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return property;
    }
}
