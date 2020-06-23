package com.example.myapplication.MKCorePack;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Player implements Serializable {
    private String nickName;
    private int mmr;
    private boolean ownPlayer;
    private PlayerStats playerStats = new PlayerStats();
    private static Player currentOwnPlayer;

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
        this.playerStats = player.playerStats;
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void setCurrentOwnPlayer(Player curOwnPlayer) {
        currentOwnPlayer = new Player(curOwnPlayer);
    }

    public static Player getCurrentOwnPlayer() {
        return currentOwnPlayer;
    }

    public static Player getPlayer(String nickName, int mmr) {
        if(currentOwnPlayer.getNickName().equals(nickName))
            return currentOwnPlayer;
        else
            return new Player(nickName, mmr);
    }

    public String getNickName() {
        return this.nickName;
    }

    public int getMmr() {
        return this.mmr;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public double getRankedWinRate() {
        return playerStats.getRankedWinRate();
    }

    public String getRankedWinRateString() {
        double wr = round(getRankedWinRate() * 100, 4);
        return wr + " %";
    }

    public double getWinRate() {
        return playerStats.getWinRate();
    }

    public String getWinRateString() {
        double wr = round(getWinRate() * 100, 4);
        return wr + " %";
    }

    public int getTotalGames() {
        return playerStats.getTotalGames();
    }

    public int getTotalGames(int characterId) {
        return playerStats.getCharacterStats(characterId).getTotalGames();
    }

    public int getTotalGamesWins() {
        return playerStats.getTotalGamesWin();
    }

    public int getTotalGamesWins(int characterId) {
        return playerStats.getCharacterStats(characterId).getTotalGamesWin();
    }

    public int getTotalRankedGamesWins() {
        return playerStats.getTotalRankedGamesWin();
    }

    public int getTotalGamesLose() {
        return playerStats.getTotalGamesLose();
    }

    public int getTotalGamesLose(int characterId) {
        return playerStats.getCharacterStats(characterId).getTotalGamesLose();
    }

    public int getTotalRankedGamesLose() {
        return playerStats.getTotalRankedGamesLose();
    }

    public int getTotalGamesDisconnects() {
        return playerStats.getTotalGamesDisconnects();
    }

    public int getTotalGamesDisconnects(int characterId) {
        return playerStats.getCharacterStats(characterId).getTotalGamesDisconnects();
    }

    public Character getFavoriteCharacter() {
        return playerStats.getFavoriteCharacter();
    }

    public void setPlayerStats(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nickName + " (" + this.mmr + " mmr)";
    }
}
