package com.example.myapplication.MKCorePack;

import java.io.Serializable;

public class DLCCharacter implements Serializable {
    private int id;
    private String title;

    public DLCCharacter() {
        this.id = 0;
        this.title = "vanilla";
    }

    public DLCCharacter(DLCCharacter dlc) {
        this.id = dlc.id;
        this.title = dlc.title;
    }

    public String getTitle() {
        return title;
    }
}
