package com.example.myapplication.MKCorePack;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Player implements Serializable {
    private String nickName;
    private int mmr;
    private boolean ownPlayer;
    private PlayerStats playerStats = new PlayerStats();

    public Player() {
        nickName = "unknown";
        mmr = 0;;
    }

    public Player(String nickName, int mmr) {
        this.nickName = nickName;
        this.mmr = mmr;
    }

    public Player(Player player) {
        this.nickName = player.nickName;
        this.mmr = player.mmr;
        this.ownPlayer = player.ownPlayer;
    }

    public void setOwnPlayer() {
        this.ownPlayer = true;
    }

    public String getNickName() {
        return this.nickName;
    }

    public int getMmr() {
        return this.mmr;
    }

    public boolean getOwnPlayer() {
        return this.ownPlayer;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public double getRankedWinRate() {
        return playerStats.getRankedWinRate();
    }

    public double getWinRate() {
        return playerStats.getWinRate();
    }

    public int getTotalGames() {
        return playerStats.getTotalGames();
    }

    public Character getFavoriteCharacter() {
        return playerStats.getFavoriteCharacter();
    }
    @NonNull
    @Override
    public String toString() {
        return this.nickName + " (" + this.mmr + " mmr)";
    }

    public void setPlayerStats(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }
}
