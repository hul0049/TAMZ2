package com.example.hul0049_tamz2_lode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hul0049_tamz2_lode.Classes.Board;
import com.example.hul0049_tamz2_lode.Classes.DBHelper;
import com.example.hul0049_tamz2_lode.Classes.GameView;
import com.example.hul0049_tamz2_lode.Classes.Player;
import com.example.hul0049_tamz2_lode.Classes.ProfileAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DBHelper mydb;
    ListView itemListView;
    Player player1 = null;
    Player player2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void PlayIt(View view)
    {
        Button pvp = findViewById(R.id.pvp);
        pvp.setVisibility(View.VISIBLE);
        Button pve = findViewById(R.id.pve);
        pve.setVisibility(View.VISIBLE);
        Button play = findViewById(R.id.Play);
        play.setVisibility(View.INVISIBLE);
        Button profiles = findViewById(R.id.Profiles);
        profiles.setVisibility(View.INVISIBLE);

        /*Player pl1 = new Player(40,"Pavel","1");
        Player pl2 = new Player(30,"Karel","1");
        Board boardForPl1 = new Board(10,pl1);
        Board boardForPl2 = new Board(10,pl2);
        boardForPl1.initilizeBoard();
        boardForPl2.initilizeBoard();*/
    }

    public void PlayerVsPlayer (View view2){

        Button pvp = findViewById(R.id.pvp);
        pvp.setVisibility(View.INVISIBLE);
        Button pve = findViewById(R.id.pve);
        pve.setVisibility(View.INVISIBLE);
        TextView text = findViewById(R.id.textView2);
        text.setVisibility(View.VISIBLE);


        mydb = new DBHelper(this);
        ArrayList<Player> arrayOfUsers;
        arrayOfUsers = mydb.getProfileList();
        ProfileAdapter profadapt = new ProfileAdapter(this, arrayOfUsers);

        itemListView = (ListView) findViewById(R.id.ListView2);
        itemListView.setAdapter(profadapt);

        itemListView.setOnItemClickListener((parent, view, position, id) -> {

            if(player1==null) {
                player1 = (Player) itemListView.getItemAtPosition(position);
                text.setText("Choose second player");
            }
            else {
                player2 = (Player) itemListView.getItemAtPosition(position);
            }

            if(player2!=null)
            {   itemListView.setVisibility(View.INVISIBLE);
                text.setVisibility(View.INVISIBLE);
                Board boardForPl1 = new Board(10,player1);
                Board boardForPl2 = new Board(10,player2);
                boardForPl1.InitializeBoard();
                boardForPl2.InitializeBoard();

                GameView gameView = findViewById(R.id.gameView);
                gameView.setLevel(player1,player2,boardForPl1,boardForPl2);
            }
        });

    }

    public void PlayerVsAI (View view){

        Button pvp = findViewById(R.id.pvp);
        pvp.setVisibility(View.INVISIBLE);
        Button pve = findViewById(R.id.pve);
        pve.setVisibility(View.INVISIBLE);

        Button easy = findViewById(R.id.Easy);
        easy.setVisibility(View.VISIBLE);
        Button normal = findViewById(R.id.Normal);
        normal.setVisibility(View.VISIBLE);
    }

    public void Profiles(View view)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {

        Button easy = findViewById(R.id.Easy);
        Button normal = findViewById(R.id.Normal);
        Button pve = findViewById(R.id.pve);
        Button pvp = findViewById(R.id.pvp);
        Button play = findViewById(R.id.Play);
        Button profiles = findViewById(R.id.Profiles);

        if(easy.getVisibility()==View.VISIBLE && normal.getVisibility()==View.VISIBLE)
        {
            easy.setVisibility(View.INVISIBLE);
            normal.setVisibility(View.INVISIBLE);
            pve.setVisibility(View.VISIBLE);
            pvp.setVisibility(View.VISIBLE);
        }
        else
        if(pvp.getVisibility()==View.VISIBLE && pve.getVisibility()==View.VISIBLE)
        {
            pve.setVisibility(View.INVISIBLE);
            pvp.setVisibility(View.INVISIBLE);
            play.setVisibility(View.VISIBLE);
            profiles.setVisibility(View.VISIBLE);
        }


    }

}