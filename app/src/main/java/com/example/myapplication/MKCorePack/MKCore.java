package com.example.myapplication.MKCorePack;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MKCore {
    ArrayList<Character> characterList = new ArrayList<>();
    String pathToInitializeCharactersList;
    DataProvider dataProvider = new DataProvider();
    List<Kombat> kombatsList = new ArrayList<>();


    private void Initialize() {
        pathToInitializeCharactersList = "";
        //InitKombatList();
        InitCharactersList();
    }

    private void InitCharactersList() {
        characterList = dataProvider.GetCharactersList(pathToInitializeCharactersList);
    }

    public void InitKombatList(Context context) {
        kombatsList = JSONHelper.importFromJSON(context);
    }


    public void setKombatsList(List<Kombat> kombatsList) {
        this.kombatsList = kombatsList;
    }

    public ArrayList<Character> GetCharactersList(){
        return characterList;
    }

    public ArrayList<Kombat> GetKombatsList() { return (ArrayList) kombatsList;}

    public boolean SetPathToInitializeCharactersList(String path){
        if(dataProvider.ValidatePath(path).IsSuccess()){
            pathToInitializeCharactersList = path;
            return true;
        }
        else{
            return false;
        }
    }
}

