package com.miro.utils;

import org.aeonbits.owner.ConfigFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static final File FILE = new File("src/test/resources/credentials.properties");
    private static final Property properties = ConfigFactory.create(Property.class, loadProperties());

    public static Property getProperties() {
        return properties;
    }

    private static Properties loadProperties() {
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