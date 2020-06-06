package com.example.myapplication.MKCorePack;


import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;


public class Character implements Serializable {
    private int id;
    private String name;
    private Variation variation = new Variation();
    private ArrayList<Variation> VariationsList = new ArrayList<>();
    private int characterResource = 0;

    public Character() {
        this.id = 0;
        this.name = "DEFAULT";
    }
    public Character(String name, Variation variation, int characterResource) {
        this.name = name;
        this.variation = new Variation(variation);
        this.characterResource = characterResource;
    }
    public Character(Character character) {
        this.id = character.id;
        this.name = character.name;
        this.variation = new Variation(character.variation);
        this.characterResource = character.characterResource;
    }
    public void SetId(int id) {
        this.id = id;
    }
    public void SetName( String name) {
        this.name = name;
    }
    public void SetVariation(Variation variation) {
        this.variation = new Variation(variation);
    }
    public void Set(Character character) {
        this.id = character.id;
        this.name = character.name;
        this.variation = new Variation(character.variation);
    }
    public void Set(int id, String name, Variation variation) {
        this.id = id;
        this.name = name;
        this.variation = new Variation(variation);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getNameVariation() {
        return this.variation.getTitle();
    }

    public int getCharacterResource() {
        return this.characterResource;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name + "(" + this.variation.getTitle() + ")";
    }
}
