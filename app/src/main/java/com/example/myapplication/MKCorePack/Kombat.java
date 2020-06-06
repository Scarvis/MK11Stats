package com.example.myapplication.MKCorePack;

import java.io.Serializable;

public class Kombat implements Serializable {
    static int idCounter = 0;
    int id = 0;
    Character leftCharacter = new Character();
    Character rightCharacter = new Character();

    Player leftPlayer = new Player();
    Player rightPlayer = new Player();

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

    public int GetId() {
        return this.id;
    }

    public Character GetLeftCharacter() {
        return this.leftCharacter;
    }

    public Character GetRightCharacter() {
        return this.rightCharacter;
    }

    public Character GetYourCharacter() {
        if (leftPlayer.getOwnPlayer()) return leftCharacter;
        if (rightPlayer.getOwnPlayer()) return rightCharacter;
        return null;
    }

    public Character GetOpponentCharacter() {
        if (leftPlayer.getOwnPlayer()) return rightCharacter;
        if (rightPlayer.getOwnPlayer()) return leftCharacter;
        return null;
    }

    public int GetResourceLeftCharacter() {
        return this.leftCharacter.getCharacterResource();
    }

    public int GetResourceRightCharacter() {
        return this.rightCharacter.getCharacterResource();
    }

    public Player getLeftPlayer() {
        return this.leftPlayer;
    }

    public Player getRightPlayer() {
        return this.rightPlayer;
    }
}
