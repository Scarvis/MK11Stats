package com.example.myapplication.MKCorePack;

import android.content.Context;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MKCore {
    private ArrayList<Character> characterList = new ArrayList<>();
    private String pathToInitializeCharactersList;
    private DataProvider dataProvider = new DataProvider();
    private ArrayList<Kombat> kombatsList = new ArrayList<>();
    //#TEST
    private Player ownPlayer = new Player("Scarvis", 1453);

    public MKCore() {
        initialize();
    }

    private void initialize() {
        //pathToInitializeCharactersList = "";
        //InitKombatList();
        initCharactersList();
        //ownPlayer.setOwnPlayer();
        initPlayer();

    }

    private void initPlayer() {
        PlayerStats playerStats = new PlayerStats(
                0.4,
                0.4,
                new Character("Sub Zero", new Variation("Ice"), R.drawable.sub_zero),
                3
        );
    }

    private void initCharactersList() {
        Character scorp = new Character("Scorpion", new Variation("Hellfire"), R.drawable.scorpion);
        Character sub = new Character("Sub Zero", new Variation("Ice"), R.drawable.sub_zero);
        characterList.add(scorp);
        characterList.add(sub);
        //characterList = dataProvider.GetCharactersList(pathToInitializeCharactersList);
    }

    public void initKombatList(Context context) {
        kombatsList = JSONHelper.importFromJSON(context);
    }

    public void addNewKombat(Kombat kombat) {

    }

    public void setKombatsList(ArrayList<Kombat> kombatsList) {
        this.kombatsList = kombatsList;
    }

    public ArrayList<Character> getCharactersList() {
        return characterList;
    }

    public ArrayList<Kombat> getKombatsList() {
        return kombatsList;
    }

    public boolean setPathToInitializeCharactersList(String path) {
        if (dataProvider.ValidatePath(path).IsSuccess()) {
            pathToInitializeCharactersList = path;
            return true;
        } else {
            return false;
        }
    }

    public Player getOwnPlayer() {
        return ownPlayer;
    }

    public int getTotalGames() {
        return kombatsList.size();
    }

    public double getRankedWinRate() {
        return ownPlayer.getRankedWinRate();
    }

    public double getWinRate() {
        return ownPlayer.getWinRate();
    }
}

