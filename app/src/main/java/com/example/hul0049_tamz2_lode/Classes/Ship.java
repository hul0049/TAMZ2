package com.example.hul0049_tamz2_lode.Classes;

import com.example.hul0049_tamz2_lode.Enums.Type;

public class Ship {
    //2 small, 2normal, 2 straight, 1 main
    private int size;
    private int life;
    private Type type;
    private int x[];
    private int y[];

    public Ship(int x[],int y[],Type type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        switch (type){
            case MAIN: this.life = 6; break;
            case STRAIGHT:
            case NORMAL:
                this.life = 4; break;
            case SMALL: this.life = 2; break;
        }

    }

    public Type getType() {
        return type;
    }

    public int getLife() {
        return life;
    }

    public int getSize() {
        return size;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setXY(int[] x,int[] y) {
        this.x = x;
        this.y = y;
    }


}

