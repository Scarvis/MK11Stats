package com.example.myapplication.MKCorePack;

import android.util.Log;

import com.example.myapplication.R;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PlayerStats implements Serializable {
    private double rankedWinRate;
    private double winRate;
    private Character favoriteCharacter;
    private int totalGames;
    private int totalGamesWin;
    private int totalGamesLose;
    private int totalRankedGames;
    private int totalRankedGamesWin;
    private int totalRankedGamesLose;
    private ArrayList<PlayerCharacterStats> playerCharacterStatsArrayList;

    public PlayerStats() {
        playerCharacterStatsArrayList = new ArrayList<>();
    }

    public PlayerStats(double rankedWinRate, double winRate, Character favoriteCharacter, int totalGames) {
        this.rankedWinRate = rankedWinRate;
        this.winRate = winRate;
        this.favoriteCharacter = favoriteCharacter;
        this.totalGames = totalGames;
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void calculate(ArrayList<Kombat> kombatArrayList) {
        int charUsedMax = 0;
        int favCharId = 0;
        totalGames = kombatArrayList.size();
        totalRankedGamesWin = 0;
        totalRankedGamesLose = 0;
        totalGamesWin = 0;
        totalGamesLose = 0;
        totalRankedGames = 0;
        for (Character character : Character.getCharacterArrayList()) {
            PlayerCharacterStats playerCharacterStats = new PlayerCharacterStats();
            playerCharacterStats.setCharacterId(character.getId());
            playerCharacterStats.calculate(kombatArrayList, character);
            if (charUsedMax < playerCharacterStats.getTotalGames()) {
                charUsedMax = playerCharacterStats.getTotalGames();
                favCharId = character.getId();
            }
            totalRankedGamesWin += playerCharacterStats.getTotalRankedGamesWin();
            totalRankedGamesLose += playerCharacterStats.getTotalRankedGamesLose();
            totalGamesWin += playerCharacterStats.getTotalGamesWin();
            totalGamesLose += playerCharacterStats.getTotalGamesLose();
            totalRankedGames += playerCharacterStats.getTotalRankedGames();
            playerCharacterStatsArrayList.add(playerCharacterStats);
        }
        winRate = round((double) totalGamesWin / totalGames, 4);
        rankedWinRate = round((double) totalRankedGamesWin / totalRankedGames, 4);
        favoriteCharacter = new Character(Character.getCharacterById(favCharId));
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

    public int getTotalGamesWin() {
        return totalGamesWin;
    }

    public int getTotalRankedGamesWin() {
        return totalRankedGamesWin;
    }

    public int getTotalGamesLose() {
        return totalGamesLose;
    }

    public int getTotalRankedGamesLose() {
        return totalRankedGamesLose;
    }

    public int getTotalGamesDisconnects() {
        return totalGames - (totalGamesLose + totalGamesWin);
    }

    public int getTotalGames(int characterId) {
        return getCharacterStats(characterId).getTotalGames(characterId);
    }

    public int getTotalRankedGames(int characterId) {
        return getCharacterStats(characterId).getTotalRankedGames(characterId);
    }

    public int getTotalGamesWin(int characterId) {
        return getCharacterStats(characterId).getTotalGamesWin(characterId);
    }

    public int getTotalRankedGamesWin(int characterId) {
        return getCharacterStats(characterId).getTotalRankedGamesWin(characterId);
    }

    public int getTotalGamesLose(int characterId) {
        return getCharacterStats(characterId).getTotalGamesLose(characterId);
    }

    public int getTotalRankedGamesLose(int characterId) {
        return getCharacterStats(characterId).getTotalRankedGamesLose(characterId);
    }

    public int getTotalGamesDisconnects(int characterId) {
        PlayerCharacterStats playerCharacterStats = getCharacterStats(characterId);
        return playerCharacterStats.getTotalGames(characterId) - (
                playerCharacterStats.getTotalGamesLose(characterId) + playerCharacterStats.getTotalGamesWin(characterId));
    }

    public PlayerCharacterStats getCharacterStats(int characterId) {
        try {
            for (int i = 0; i < playerCharacterStatsArrayList.size(); i++) {
                if (playerCharacterStatsArrayList.get(i).getCharacterId() == characterId)
                    return playerCharacterStatsArrayList.get(i);
            }
            return new PlayerCharacterStats();
        }
        catch (Exception e) {
            Log.e("PlayerStats ", Objects.requireNonNull(e.getMessage()));
            return null;
        }
    }
}
