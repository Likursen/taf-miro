package com.miro.utils;

import org.aeonbits.owner.Config;

/**
 * User: e.yakovets
 * Date: 31.08.22
 */
public interface Property extends Config {

    @Key("token")
    String token();

    @Key("login")
    String login();

    @Key("password")
    String password();
}