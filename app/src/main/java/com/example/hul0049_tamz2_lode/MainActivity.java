package com.example.hul0049_tamz2_lode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        Player pl1 = new Player(40,"Pavel","1");
        Player pl2 = new Player(30,"Karel","1");
        Board boardForPl1 = new Board(10,pl1);
        Board boardForPl2 = new Board(10,pl2);
        boardForPl1.initilizeBoard();
        boardForPl2.initilizeBoard();


    }
    public void Profiles(View view)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);

    }

}