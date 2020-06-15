package com.example.myapplication.MKCorePack;

import java.io.Serializable;

public class Variation implements Serializable {
    private int id = 0;
    private String title;

    public Variation() {
        title = "CUSTOM";
    }

    public Variation(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Variation(String title) {
        this.title = title;
    }

    public Variation(Variation variation) {
        this.id = variation.id;
        this.title = variation.title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
