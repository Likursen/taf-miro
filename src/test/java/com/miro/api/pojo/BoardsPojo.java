package com.miro.api.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class BoardsPojo {
    private ArrayList<Datum> data;

    @Getter
    @Setter
    public static class Datum {
        private String id;
        private String name;
    }
}