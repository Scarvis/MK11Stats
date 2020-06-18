package com.example.myapplication.MKCorePack;

import com.example.myapplication.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class PlayerStats implements Serializable {
    private double rankedWinRate;
    private double winRate;
    private Character favoriteCharacter = new Character();
    private int totalGames;

    public PlayerStats() {

    }

    public PlayerStats(double rankedWinRate, double winRate, Character favoriteCharacter, int totalGames) {
        this.rankedWinRate = rankedWinRate;
        this.winRate = winRate;
        this.favoriteCharacter = favoriteCharacter;
        this.totalGames = totalGames;
    }

    public double getRankedWinRate() {
        return rankedWinRate;
    }

    public double getWinRate() {
        return winRate;
    }

    public Character getFavoriteCharacter() {
        return favoriteCharacter;
    }

    public int getTotalGames() {
        return totalGames;
    }
}
