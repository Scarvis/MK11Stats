package com.example.myapplication.MKCorePack;

import android.content.Context;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MKCore {
    ArrayList<Character> characterList = new ArrayList<>();
    String pathToInitializeCharactersList;
    DataProvider dataProvider = new DataProvider();
    List<Kombat> kombatsList = new ArrayList<>();
    //#TEST
    Player ownPlayer = new Player("Scarvis", 1453);

    public MKCore() {
        initialize();
    }

    private void initialize() {
        pathToInitializeCharactersList = "";
        //InitKombatList();
        InitCharactersList();
        ownPlayer.setOwnPlayer();
    }

    private void InitCharactersList() {
        Character scorp = new Character("Scorpion", new Variation("Hellfire"), R.drawable.scorpion);
        Character sub = new Character("Sub Zero", new Variation("Ice"), R.drawable.sub_zero);
        characterList.add(scorp);
        characterList.add(sub);
        //characterList = dataProvider.GetCharactersList(pathToInitializeCharactersList);
    }

    public void InitKombatList(Context context) {
        kombatsList = JSONHelper.importFromJSON(context);
    }

    public void addNewKombat(Kombat kombat) {

    }

    public void setKombatsList(List<Kombat> kombatsList) {
        this.kombatsList = kombatsList;
    }

    public ArrayList<Character> GetCharactersList() {
        return characterList;
    }

    public ArrayList<Kombat> GetKombatsList() {
        return (ArrayList) kombatsList;
    }

    public boolean SetPathToInitializeCharactersList(String path) {
        if (dataProvider.ValidatePath(path).IsSuccess()) {
            pathToInitializeCharactersList = path;
            return true;
        } else {
            return false;
        }
    }
}

