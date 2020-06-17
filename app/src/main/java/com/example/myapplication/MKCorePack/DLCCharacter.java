package com.example.myapplication.MKCorePack;

import java.io.Serializable;

public class DLCCharacter implements Serializable {
    private int id;
    private String title;
    private String releaseDate;

    public DLCCharacter() {
        this.id = 0;
        this.title = "vanilla";
    }

    public DLCCharacter(DLCCharacter dlc) {
        this.id = dlc.id;
        this.title = dlc.title;
        this.releaseDate = dlc.releaseDate;
    }

    public DLCCharacter(String title, String releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }
}
