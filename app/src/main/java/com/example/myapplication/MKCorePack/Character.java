package com.example.myapplication.MKCorePack;


import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;


public class Character implements Serializable {
    private int id;
    private String name;
    private Variation variation = new Variation();
    private ArrayList<Variation> variationsList = new ArrayList<>();
    private int characterImageResource = 0;
    private DLCCharacter dlc = new DLCCharacter();


    private static ArrayList<Character> characterArrayList = new ArrayList<Character>();

    public Character() {
        this.id = 0;
        this.name = "DEFAULT";
    }
    public Character(String name, Variation variation, int characterImageResource) {
        this.name = name;
        this.variation = new Variation(variation);
        this.variationsList.add(variation);
        this.characterImageResource = characterImageResource;
    }

    public Character(int id, String name, Variation variation, int characterImageResource) {
        this.id = id;
        this.name = name;
        this.variation = variation;
        this.characterImageResource = characterImageResource;
    }

    public Character(int id, String name, ArrayList<Variation> variationsList, int characterImageResource, DLCCharacter dlc) {
        this.id = id;
        this.name = name;
        this.variationsList = variationsList;
        this.characterImageResource = characterImageResource;
        this.dlc = dlc;
    }

    public Character(Character character) {
        this.id = character.id;
        this.name = character.name;
        this.variation = new Variation(character.variation);
        this.characterImageResource = character.characterImageResource;
        this.variationsList = character.variationsList;
        this.dlc = new DLCCharacter(character.dlc);
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName( String name) {
        this.name = name;
    }
    public void setVariation(Variation variation) {
        this.variation = new Variation(variation);
    }
    public void setVariation(int id) {
        for(Variation var : variationsList) {
            if(var.getId() == id) {
                variation = new Variation(var);
                return;
            }
        }
        variation = new Variation();
    }
    public void setCharacterImageResource(int characterImageResource){
        this.characterImageResource = characterImageResource;
    }

    public static void setCharacterArrayList(ArrayList<Character> characters) {
        characterArrayList = characters;
    }

    public static ArrayList<Character> getCharacterArrayList() {
        return characterArrayList;
    }

    public static Character getCharacterById(int id) {
        for (int i = 0; i < characterArrayList.size(); i++) {
            if (characterArrayList.get(i).getId() == id)
                return characterArrayList.get(i);
        }
        return new Character();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Variation getVariation() {
        return this.variation;
    }

    public Variation getVariation(int id) {
        for (Variation var : variationsList) {
            if (var.getId() == id)
                return var;
        }
        return new Variation();
    }

    public ArrayList<Variation> getVariationsList() {
        return this.variationsList;
    }

    public int getCharacterResource() {
        return this.characterImageResource;
    }

    public DLCCharacter getDlc() {
        return dlc;
    }

    public static Comparator<Character> CharactersIdComparator = new Comparator<Character>() {

        public int compare(Character character1, Character character2) {
            Integer id1 = character1.getId();
            Integer id2 = character2.getId();
            return id1.compareTo(id2);
        }};
    public static Comparator<Character> CharactersNameComparator = new Comparator<Character>() {

        public int compare(Character character1, Character character2) {
            String id1 = character1.getName();
            String id2 = character2.getName();
            return id1.compareTo(id2);
        }};

    @NonNull
    @Override
    public String toString() {
        return this.name + "(" + this.variation.getTitle() + ")";
    }
}
