package com.example.hul0049_tamz2_lode.Classes;

import com.example.hul0049_tamz2_lode.Classes.Ship;

public class Player {
    private String nick;
    private String score;
    private Ship ships[];

    public Player(String nick, int number)
    {
        this.nick = nick;
        ships = new Ship[number];
    }
    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setShips(Ship ship, int number) {
        this.ships[number] = ship;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Ship[] getShips() {
        return ships;
    }

    public String getScore() {
        return score;
    }

    public String getNick() {
        return nick;
    }
}