package com.example.myapplication.ViewModels;

import android.content.Context;

import com.example.myapplication.Adapters.DatabaseAdapter;
import com.example.myapplication.MKCorePack.Kombat;

import java.io.Serializable;
import java.util.ArrayList;

public class KombatsListViewModel implements Serializable {
    private ArrayList<Kombat> kombatArrayList;

    public KombatsListViewModel(ArrayList<Kombat> kombatArrayList) {
        this.kombatArrayList = kombatArrayList;
    }

    public KombatsListViewModel(KombatsListViewModel kombatsListViewModel) {
        this.kombatArrayList = kombatsListViewModel.kombatArrayList;
    }

    public void addKombat(Kombat kombat) {
        kombatArrayList.add(kombat);
    }

    public void setKombatArrayList(ArrayList<Kombat> kombatArrayList) {
        this.kombatArrayList = kombatArrayList;
    }

    public ArrayList<Kombat> getKombatArrayList() {
        return kombatArrayList;
    }

    public int getCountKombats() {
        return kombatArrayList.size();
    }
}
