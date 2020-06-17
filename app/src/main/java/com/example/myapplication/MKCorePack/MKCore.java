package com.example.myapplication.MKCorePack;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.Adapters.DatabaseAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MKCore {
    private ArrayList<Character> characterList = new ArrayList<>();
    private ArrayList<Kombat> kombatsList = new ArrayList<>();
    private DatabaseAdapter databaseAdapter;
    //#TEST
    private Player ownPlayer = new Player("Scarvis", 1453);

    public MKCore() {
        initialize();
    }

    public MKCore(Context context) {
        initialize();
        initKombatList(context);
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
                new Character(2,"Scorpion", new Variation("Reborn"), R.drawable.scorpion),
                3
        );
        ownPlayer.setPlayerStats(playerStats);
        Player.setCurrentOwnPlayer(ownPlayer);
    }

    private void initCharactersList() {
//        ArrayList<Variation> arr = new ArrayList<>();
//        arr.add(new Variation(1, "Searing Rage"));
//        arr.add(new Variation(2, "Reborn"));
//        arr.add(new Variation(3, "Burning Specter"));
//        Character scorp = new Character(1,"Scorpion", arr, R.drawable.scorpion, new DLCCharacter());
//        ArrayList<Variation> arr2 = new ArrayList<>();
//        arr2.add(new Variation(1, "Dead of Winter"));
//        arr2.add(new Variation(2, "Thin Ice"));
//        arr2.add(new Variation(3, "Avalanche"));
//        Character sub = new Character(2, "Sub Zero", arr2, R.drawable.sub_zero, new DLCCharacter());
//        characterList.add(scorp);
//        characterList.add(sub);
    }

    public void initKombatList(Context context) {
        databaseAdapter = new DatabaseAdapter(context);
    }

    public void addNewKombat(Kombat kombat) {
        databaseAdapter.open();
        databaseAdapter.insert(kombat);
        databaseAdapter.close();
    }

    public void setCharacterList(String text) {
        ArrayList<Character> charArrayList = JSONHelper.importCharactersFromJSON(text);
        if (charArrayList == null) return;
        for (Character character : charArrayList) {
            Log.d("CHARACTERES", character.getName() + " " + character.getId());
            if (character.getId() == 1)
                character.setCharacterImageResource(R.drawable.scorpion);
            if (character.getId() == 2)
                character.setCharacterImageResource(R.drawable.sub_zero);
        }
        characterList = charArrayList;
        Character.setCharacterArrayList(characterList);
    }

    public void setKombatsList(ArrayList<Kombat> kombatsList) {
        this.kombatsList = kombatsList;
    }

    public ArrayList<Character> getCharactersList() {
        return characterList;
    }

    public ArrayList<Kombat> getKombatsList() {
        databaseAdapter.open();
        kombatsList = databaseAdapter.getKombats();
        databaseAdapter.close();
        return kombatsList;
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

