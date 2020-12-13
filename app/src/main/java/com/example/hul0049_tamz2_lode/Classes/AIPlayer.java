package com.example.hul0049_tamz2_lode.Classes;

import com.example.hul0049_tamz2_lode.Enums.Difficult;

public class AIPlayer extends Player{
    private Difficult difficult;

    public AIPlayer(Difficult difficult) {
        super(-1, "BOT", "0");
        this.difficult = difficult;
    }

    @Override
    public Difficult getDifficult() {
        return difficult;
    }

    @Override
    public void setDifficult(Difficult difficult) {
        this.difficult = difficult;
    }
}
