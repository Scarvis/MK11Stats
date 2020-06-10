package com.example.myapplication.ViewModels;

import com.example.myapplication.MKCorePack.Kombat;

import java.io.Serializable;
import java.util.ArrayList;

public class KombatsListViewModel implements Serializable {
    private ArrayList<Kombat> kombatArrayList = new ArrayList<Kombat>();

    public KombatsListViewModel(ArrayList<Kombat> kombatArrayList) {
        this.kombatArrayList = kombatArrayList;
    }

    public KombatsListViewModel(KombatsListViewModel kombatsListViewModel) {
        this.kombatArrayList = kombatsListViewModel.kombatArrayList;
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
