package com.example.hul0049_tamz2_lode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.hul0049_tamz2_lode.Classes.Board;
import com.example.hul0049_tamz2_lode.Classes.Player;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void Nazev(View view)
    {
        Player pl1 = new Player("Pavel",7);
        Player pl2 = new Player("Karel",7);
        Board boardForPl1 = new Board(10,pl1);
        Board boardForPl2 = new Board(10,pl2);
        boardForPl1.initilizeBoard();
        int b =4;
        boardForPl2.initilizeBoard();
        int a;

    }


}