package com.example.myapplication.ViewModels;

import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.MKCorePack.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel implements Serializable {
    private int totalGames;
    private double winRate;
    private double rankedWinRate;
    private Character favoriteCharacter;
    private ArrayList<Kombat> kombatList;
    private Player onwPlayer;

    public ProfileViewModel(ProfileViewModel profileViewModel) {
        this.totalGames = profileViewModel.totalGames;
        this.winRate = profileViewModel.winRate;
        this.rankedWinRate = profileViewModel.rankedWinRate;
        this.favoriteCharacter = profileViewModel.favoriteCharacter;
        this.kombatList = profileViewModel.kombatList;
        this.onwPlayer = profileViewModel.onwPlayer;
    }

    public ProfileViewModel(int totalGames, double winRate, double rankedWinRate, Character favoriteCharacter, ArrayList kombatList, Player ownPlayer) {
        this.totalGames = totalGames;
        this.winRate = winRate;
        this.rankedWinRate = rankedWinRate;
        this.favoriteCharacter = favoriteCharacter;
        this.kombatList = kombatList;
        this.onwPlayer = ownPlayer;
    }

    public Player getOnwPlayer() {
        return onwPlayer;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public double getWinRate() {
        return winRate;
    }

    public double getRankedWinRate() {
        return rankedWinRate;
    }

    public Character getFavoriteCharacter() {
        return favoriteCharacter;
    }

    public List<Kombat> getKombatList() {
        return kombatList;
    }

    public int getCountKombats() {
        return kombatList.size();
    }
}
