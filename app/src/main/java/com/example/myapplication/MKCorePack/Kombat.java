package com.example.myapplication.MKCorePack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Kombat implements Serializable {
    private static int idCounter = 0;
    private long id = 0;
    private Character leftCharacter = new Character();
    private Character rightCharacter = new Character();

    private Player leftPlayer = new Player();
    private Player rightPlayer = new Player();

    private boolean isRanked = false;
    private int kombatLeagueSeason = 0;

    private WINNER_SIDE winnerSide = WINNER_SIDE.OWN;

    public Kombat() {
        this.id = Kombat.idCounter;
        Kombat.idCounter++;

    }

    public Kombat(Character leftCharacter, Character rightCharacter) {
        if (this.id != Kombat.idCounter) {
            Kombat.idCounter++;
            this.id = Kombat.idCounter;
        }
        this.leftCharacter = new Character(leftCharacter);
        this.rightCharacter = new Character(rightCharacter);
    }

    public Kombat(Character leftCharacter, Character rightCharacter, Player leftPlayer, Player rightPlayer) {
        if (this.id != Kombat.idCounter) {
            Kombat.idCounter++;
            this.id = Kombat.idCounter;
        }
        this.leftCharacter = new Character(leftCharacter);
        this.rightCharacter = new Character(rightCharacter);
        this.leftPlayer = new Player(leftPlayer);
        this.rightPlayer = new Player(rightPlayer);
    }

    public Kombat(Character leftCharacter, Character rightCharacter, Player leftPlayer, Player rightPlayer,
                  boolean isRanked, int kombatLeagueSeason, int winnerSide) {
        this.leftCharacter = leftCharacter;
        this.rightCharacter = rightCharacter;
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
        this.isRanked = isRanked;
        this.kombatLeagueSeason = kombatLeagueSeason;
        this.winnerSide = WINNER_SIDE.getSide(winnerSide);
    }

    public Kombat(long id, Character leftCharacter, Character rightCharacter, Player leftPlayer, Player rightPlayer,
                  boolean isRanked, int kombatLeagueSeason, int winnerSide) {
        this.id = id;
        this.leftCharacter = leftCharacter;
        this.rightCharacter = rightCharacter;
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
        this.isRanked = isRanked;
        this.kombatLeagueSeason = kombatLeagueSeason;
        this.winnerSide = WINNER_SIDE.getSide(winnerSide);
    }

    public long getId() {
        return this.id;
    }

    public Character getLeftCharacter() {
        return this.leftCharacter;
    }

    public Character getRightCharacter() {
        return this.rightCharacter;
    }

    public int getResourceLeftCharacter() {
        return this.leftCharacter.getCharacterResource();
    }

    public int getResourceRightCharacter() {
        return this.rightCharacter.getCharacterResource();
    }

    public Player getLeftPlayer() {
        return this.leftPlayer;
    }

    public Player getRightPlayer() {
        return this.rightPlayer;
    }

    public int getIsRankedInt() {
        if(isRanked) return 1;
        return 0;
    }

    public boolean isRanked() {
        return isRanked;
    }

    public int getKombatLeagueSeason() {
        return kombatLeagueSeason;
    }

    public int getIntWinnerSide() {
        return WINNER_SIDE.getIntSide(winnerSide);
    }

    public WINNER_SIDE getWinnerSide() {
        return winnerSide;
    }

    public enum WINNER_SIDE {
        OWN,
        OPPONENT,
        OWN_LEAVE,
        OPPONENT_LEAVE,
        SERVER_ERROR;
        public static WINNER_SIDE getSide(int id) {
            switch (id) {
                case 1 : return WINNER_SIDE.OWN;
                case 2 : return WINNER_SIDE.OPPONENT;
                case 3 : return WINNER_SIDE.OWN_LEAVE;
                case 4 : return WINNER_SIDE.OPPONENT_LEAVE;
                default: return SERVER_ERROR;
            }
        }
        public static int getIntSide(WINNER_SIDE winner_side) {
            if(winner_side == WINNER_SIDE.OWN) return 1;
            if(winner_side == WINNER_SIDE.OPPONENT) return 2;
            if(winner_side == WINNER_SIDE.OWN_LEAVE) return 3;
            if(winner_side == WINNER_SIDE.OPPONENT_LEAVE) return 4;
            return 0;
        }
        public static int getIdSideByString(String sideOption) {
            ArrayList<String> options = getValuesSimpleStrings();
            for (int i = 0; i < options.size(); i++) {
                if (sideOption.equals(options.get(i)))
                    return i + 1;
            }
            return 0;
        }
        public static ArrayList<String> getValuesSimpleStrings() {
            return new ArrayList<String> (Arrays.asList("OWN", "OPPONENT", "OWN_LEAVE", "OPPONENT_LEAVE", "SERVER_ERROR"));
        }
    }
}
