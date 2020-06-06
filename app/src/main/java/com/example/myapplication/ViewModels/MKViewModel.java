package com.example.myapplication.ViewModels;


import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.Adapters.KombatAdapter;
import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.MKCorePack.MKCore;
import com.example.myapplication.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MKViewModel extends AppCompatActivity {
    MKCore mkCore;

    ListView kombatListView;

    private static final String TAG = "myLogs";

    public MKViewModel() {
        Initialize();
    }

    private void Initialize() {
        mkCore = new MKCore();
        initKombatsList();
        initButtons();

    }

    private void initButtons() {

    }


    private void initKombatsList() {
//        try {
//            ListView kombatListView = findViewById(R.id.KombatListViewId);
//            ArrayList<Kombat> kombatArrayList = new ArrayList<>(mkCore.GetKombatsList());
//            KombatAdapter kombatAdapter = new KombatAdapter(this, R.layout.kombat_list_item, kombatArrayList);
//            kombatListView.setAdapter(kombatAdapter);
//        } catch (Exception e) {
//            Log.d(TAG, e.getMessage());
//        }
    }

    public void addNewKombat() {

    }

    public void setKombatsList(List<Kombat> kombatsList) {
        mkCore.setKombatsList(kombatsList);
    }

    public ArrayList<Character> GetCharactersList(){
        return mkCore.GetCharactersList();
    }

    public ArrayList<Kombat> GetKombatsList() { return mkCore.GetKombatsList();}

    public boolean SetPathToInitializeCharactersList(String path){
        return mkCore.SetPathToInitializeCharactersList(path);
    }
}
