package com.example.hul0049_tamz2_lode.Classes;

import com.example.hul0049_tamz2_lode.Classes.Ship;

public class Player {
    private int id;
    private String nick;
    private String score;
    private Ship ships[];
    private int number = 7;

    public Player(int id,String nick, String score)
    {
        this.id=id;
        this.nick = nick;
        this.score = score;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}