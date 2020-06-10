package com.example.myapplication.MKCorePack;

import java.io.Serializable;

public class Variation implements Serializable {
    private String title;

    public Variation() {
        title = "DEFAULT";
    }

    public Variation(String title) {
        this.title = title;
    }

    public Variation(Variation variation) {
        this.title = variation.title;
    }

    public String getTitle() {
        return title;
    }
}
