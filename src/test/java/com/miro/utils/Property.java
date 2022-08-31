package com.miro.utils;

import org.aeonbits.owner.Config;

public interface Property extends Config {

    @Key("token")
    String token();

    @Key("login")
    String login();

    @Key("password")
    String password();
}