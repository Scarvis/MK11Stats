package com.example.myapplication.ViewModels;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.MKCorePack.MKCore;
import com.example.myapplication.MKCorePack.Player;

import java.util.ArrayList;

public class MKViewModel extends AppCompatActivity {
    private MKCore mkCore;
    private ProfileViewModel profileViewModel;
    private KombatsListViewModel kombatsListViewModel;

    private static final String TAG = "myLogs";

    public MKViewModel() {
        initialize();
    }

    private void initialize() {
        mkCore = new MKCore();
        initKombatsList();
        initButtons();
        initProfileViewModel();
        initKombatsListViewModel();
    }

    private void initProfileViewModel() {
        Player player = mkCore.getOwnPlayer();
        int totalGames = mkCore.getTotalGames();
        Character favoriteCharacter = new Character(player.getFavoriteCharacter());
        double rankedWinRate = mkCore.getRankedWinRate();
        double winRate = mkCore.getWinRate();

        profileViewModel = new ProfileViewModel(
                totalGames,
                winRate,
                rankedWinRate,
                favoriteCharacter,
                mkCore.getKombatsList(),
                player
        );
    }

    private void initKombatsListViewModel() {
        kombatsListViewModel = new KombatsListViewModel(mkCore.getKombatsList());
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

    public void setKombatsList(ArrayList<Kombat> kombatsList) {
        mkCore.setKombatsList(kombatsList);
        kombatsListViewModel.setKombatArrayList(kombatsList);
    }

    public ArrayList<Character> getCharactersList() {
        return mkCore.getCharactersList();
    }

    public ArrayList getKombatsList() {
        return mkCore.getKombatsList();
    }

    public ProfileViewModel getProfileViewModel() {
        return profileViewModel;
    }

    public KombatsListViewModel getKombatsListViewModel() {
        return kombatsListViewModel;
    }
}
