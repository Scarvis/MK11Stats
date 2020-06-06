package com.example.myapplication.MKCorePack;

import java.util.ArrayList;



public class DataProvider {
    public ArrayList<Character> GetCharactersList(String path){
        ArrayList<Character> result = new ArrayList<>();

        result.add(GetCharacter(""));

        return result;
    }

    private Character GetCharacter(String text) {
        Character character = new Character();

        character.SetId(1);
        character.SetName("Scorpion");
        character.SetVariation(new Variation("Hellfire"));

        return character;
    }


    public Response ValidatePath(String path){
        // validate

        return new Response(true, path, false);
    }

    public ArrayList<Kombat> GetStats() {
        ArrayList<Kombat> kombatsList = new ArrayList<>();

        return kombatsList;
    }
}
