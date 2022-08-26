package com.miro.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {

    public static String generateRandomSting() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    public static String generateRandomEmail() {
        return RandomStringUtils.randomAlphabetic(8)+"@mail.org";
    }
}