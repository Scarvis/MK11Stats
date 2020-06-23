package com.example.myapplication.MKCorePack;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerCharacterStats implements Serializable {
    private ArrayList<Long> kombatsId;
    private NemezidaStats nemezidaStats;
    private int characterId;
    private int totalGames;
    private int totalRankedGames;
    private int totalGamesWin;
    private int totalRankedGamesWin;
    private int totalDisconnects;

    public PlayerCharacterStats() {
        this.kombatsId = new ArrayList<> ();
        this.nemezidaStats = new NemezidaStats();
        this.totalGames = 0;
        this.totalGamesWin = 0;
        this.totalRankedGamesWin = 0;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public void calculate(ArrayList<Kombat> kombatArrayList, Character character) {
        for (Kombat kombat : kombatArrayList) {
            if (kombat.getLeftCharacter().getId() != character.getId()) continue;
            totalGames++;
            if(kombat.getWinnerSide() == Kombat.WINNER_SIDE.SERVER_ERROR) totalDisconnects++;
            if(kombat.getWinnerSide() == Kombat.WINNER_SIDE.OWN || kombat.getWinnerSide() == Kombat.WINNER_SIDE.OPPONENT_LEAVE)
                totalGamesWin++;
            if (kombat.isRanked()) {
                totalRankedGames++;
                if(kombat.getWinnerSide() == Kombat.WINNER_SIDE.OWN || kombat.getWinnerSide() == Kombat.WINNER_SIDE.OPPONENT_LEAVE)
                    totalRankedGamesWin++;
            }
            kombatsId.add(kombat.getId());
            nemezidaStats.set(kombat.getRightCharacter().getId(), kombat.isRanked(), kombat.getWinnerSide());
        }
    }

    public ArrayList<Long> getKombatsId() {
        return kombatsId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getTotalRankedGames() {
        return totalRankedGames;
    }

    public int getTotalGamesWin() {
        return totalGamesWin;
    }

    public int getTotalRankedGamesWin() {
        return totalRankedGamesWin;
    }

    public int getTotalGamesLose() {
        return totalGames - totalGamesWin;
    }

    public int getTotalRankedGamesLose() {
        return totalRankedGames - totalRankedGamesWin;
    }

    public int getTotalGamesDisconnects() {
        return totalDisconnects;
    }

    public int getTotalGames(int characterId) {
        return nemezidaStats.getTotalGames(characterId);
    }

    public int getTotalRankedGames(int characterId) {
        return nemezidaStats.getTotalRankedGames(characterId);
    }

    public int getTotalGamesWin(int characterId) {
        return nemezidaStats.getTotalGamesWin(characterId);
    }

    public int getTotalRankedGamesWin(int characterId) {
        return nemezidaStats.getTotalRankedGamesWin(characterId);
    }

    public int getTotalGamesLose(int characterId) {
        return totalGames - totalGamesWin;
    }

    public int getTotalRankedGamesLose(int characterId) {
        return totalRankedGames - totalRankedGamesWin;
    }

    public int getTotalGamesDisconnects(int characterId) {
        return totalDisconnects;
    }

    private static class NemezidaStats {
        private ArrayList<Integer> charactersId;
        private ArrayList<Integer> totalGames;
        private ArrayList<Integer> totalRankedGames;
        private ArrayList<Integer> totalGamesWin;
        private ArrayList<Integer> totalRankedGamesWin;
        private ArrayList<Integer> totalGamesLose;
        private ArrayList<Integer> totalRankedGamesLose;

        public NemezidaStats() {
            this.charactersId = new ArrayList<>();
            this.totalGames = new ArrayList<>();
            this.totalRankedGames = new ArrayList<>();
            this.totalGamesWin = new ArrayList<>();
            this.totalRankedGamesWin = new ArrayList<>();
            this.totalRankedGamesLose = new ArrayList<>();
            this.totalGamesLose = new ArrayList<>();
        }

        private int getIndex(int characterId) {
            int index = -1;
            for (int i = 0; i < charactersId.size(); i++) {
                if (charactersId.get(i) == characterId) {
                    index = i;
                    break;
                }
            }
            return index;
        }

        public ArrayList<Integer> getCharactersId() {
            return charactersId;
        }

        public int getTotalGames(int characterId) {
            int index = getIndex(characterId);
            if (index == -1) return 0;
            return totalGames.get(index);
        }

        public int getTotalRankedGames(int characterId) {
            int index = getIndex(characterId);
            if (index == -1) return 0;
            return totalRankedGames.get(index);
        }

        public int getTotalGamesWin(int characterId) {
            int index = getIndex(characterId);
            if (index == -1) return 0;
            return totalGamesWin.get(index);
        }

        public int getTotalGamesLose(int characterId) {
            int index = getIndex(characterId);
            if (index == -1) return 0;
            return totalGamesLose.get(index);
        }

        public int getTotalRankedGamesWin(int characterId) {
            int index = getIndex(characterId);
            if (index == -1) return 0;
            return totalRankedGamesWin.get(index);
        }

        public int getTotalRankedGamesLose(int characterId) {
            int index = getIndex(characterId);
            if (index == -1) return 0;
            return totalRankedGamesLose.get(index);
        }

        public void set(int characterId, boolean isRanked, Kombat.WINNER_SIDE winner_side) {
            boolean added = false;
            boolean leftWinner = winner_side == Kombat.WINNER_SIDE.OWN | winner_side == Kombat.WINNER_SIDE.OPPONENT_LEAVE;
            boolean rightWinner = winner_side == Kombat.WINNER_SIDE.OPPONENT | winner_side == Kombat.WINNER_SIDE.OWN_LEAVE;
            for (int i = 0; i < charactersId.size(); i++) {
                if (charactersId.get(i) == characterId) {
                    added = true;
                    totalGames.set(i, totalGames.get(i) + 1);
                    if (leftWinner)
                        totalGamesWin.set(i, totalGamesWin.get(i) + 1);
                    if(rightWinner)
                        totalGamesLose.set(i, totalGamesLose.get(i) + 1);
                    if (isRanked) {
                        totalRankedGames.set(i, totalRankedGames.get(i) + 1);
                        if (leftWinner)
                            totalRankedGamesWin.set(i, totalRankedGamesWin.get(i) + 1);
                        if(rightWinner)
                            totalRankedGamesLose.set(i, totalRankedGamesLose.get(i) + 1);
                    }
                    break;
                }
            }
            if(!added) {
                charactersId.add(characterId);
                totalGames.add(1);
                if(isRanked) {
                    totalRankedGames.add(1);
                    if(leftWinner) {
                        totalRankedGamesWin.add(1);
                        totalRankedGamesLose.add(0);
                    }
                    else if(rightWinner) {
                        totalRankedGamesWin.add(0);
                        totalRankedGamesLose.add(1);
                    }
                    else{
                        totalRankedGamesWin.add(0);
                        totalRankedGamesLose.add(0);
                    }
                }
                else {
                    totalRankedGames.add(0);
                    totalRankedGamesWin.add(0);
                    totalRankedGamesLose.add(0);
                }
                if (leftWinner) {
                    totalGamesWin.add(1);
                    totalGamesLose.add(0);
                }
                else if(rightWinner){
                    totalGamesWin.add(0);
                    totalGamesLose.add(1);
                }
                else {
                    totalGamesWin.add(0);
                    totalGamesLose.add(0);
                }
            }
        }
    }
}
