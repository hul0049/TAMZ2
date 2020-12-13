package com.example.hul0049_tamz2_lode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hul0049_tamz2_lode.Classes.AIPlayer;
import com.example.hul0049_tamz2_lode.Classes.Board;
import com.example.hul0049_tamz2_lode.Classes.DBHelper;
import com.example.hul0049_tamz2_lode.Classes.GameView;
import com.example.hul0049_tamz2_lode.Classes.Player;
import com.example.hul0049_tamz2_lode.Classes.ProfileAdapter;
import com.example.hul0049_tamz2_lode.Enums.Difficult;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DBHelper mydb;
    ListView itemListView;
    Player player1 = null;
    Player player2 = null;
    AIPlayer player = null;
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
        if(arrayOfUsers.size()<2)
        {
            onBackPressed();
        }
        itemListView.setAdapter(profadapt);

        itemListView.setOnItemClickListener((parent, view, position, id) -> {

            if(player1==null) {
                player1 = (Player) itemListView.getItemAtPosition(position);
                text.setText("Choose second player");
                ArrayList<Player> arrayOfUsers2;
                arrayOfUsers2 = mydb.getProfileListWithOutPlayer(player1);
                ProfileAdapter profadapt2 = new ProfileAdapter(this, arrayOfUsers2);
                itemListView.setAdapter(profadapt2);
            }
            else {
                player2 = (Player) itemListView.getItemAtPosition(position);
            }

            if(player2!=null) {
                itemListView.setVisibility(View.INVISIBLE);
                text.setVisibility(View.INVISIBLE);
                Board boardForPl1 = new Board(10,player1);
                Board boardForPl2 = new Board(10,player2);
                boardForPl1.InitializeBoard();
                boardForPl2.InitializeBoard();

                GameView gameView = findViewById(R.id.gameView);
                gameView.setLevel(player1,player2,boardForPl1,boardForPl2);

                Button end = findViewById(R.id.End);
                end.setVisibility(View.VISIBLE);
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
        Button end = findViewById(R.id.End);
        end.setVisibility(View.VISIBLE);
    }

    public void Profiles(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    public void PlayerVsAIEasy(View view){
        GameVsAI(Difficult.EASY);
    }
    public void PlayerVsAINormal(View view){
        GameVsAI(Difficult.NORMAL);
    }

    public void GameVsAI(Difficult difficult) {

        Button easy = findViewById(R.id.Easy);
        easy.setVisibility(View.INVISIBLE);
        Button normal = findViewById(R.id.Normal);
        normal.setVisibility(View.INVISIBLE);
        TextView text = findViewById(R.id.textView2);
        text.setText("Choose player");
        text.setVisibility(View.VISIBLE);

        mydb = new DBHelper(this);
        ArrayList<Player> arrayOfUsers;
        arrayOfUsers = mydb.getProfileList();
        ProfileAdapter profadapt = new ProfileAdapter(this, arrayOfUsers);
        itemListView = (ListView) findViewById(R.id.ListView2);
        if(arrayOfUsers.size()<1)
        {
            onBackPressed();
        }
        itemListView.setAdapter(profadapt);
        itemListView.setOnItemClickListener((parent, view, position, id) -> {

            player1 = (Player) itemListView.getItemAtPosition(position);
            player = new AIPlayer(difficult);
            itemListView.setVisibility(View.INVISIBLE);
            text.setVisibility(View.INVISIBLE);
            Board boardForPl1 = new Board(10,player1);
            Board boardForAIPl = new Board(10,player);
            boardForPl1.InitializeBoard();
            boardForAIPl.InitializeBoard();

            GameView gameView = findViewById(R.id.gameView);
            gameView.setLevel(player1,player,boardForPl1,boardForAIPl);

        });
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
    public void end(View view) {
        GameView gameView = findViewById(R.id.gameView);
        if(gameView.getWin()) {
            Player pl1 = gameView.getWinner();
            Player pl2 = gameView.getLooser();
            if(pl1.getId()!=-1)
            {
            String score1 = pl1.getScore();
            String[] splitscore1 = score1.split(":");
            int a1 = Integer.parseInt(splitscore1[0]);
            int b1 = Integer.parseInt(splitscore1[1]);
            a1++;
            String savescore1 =a1+":"+b1;
            mydb.updateProfile(pl1.getId(), pl1.getNick(),savescore1);
            }

            if(pl2.getId()!=-1)
            {
                String score2 = pl2.getScore();
                String[] splitscore2 = score2.split(":");
                int a2 = Integer.parseInt(splitscore2[0]);
                int b2 = Integer.parseInt(splitscore2[1]);
                b2++;
                String savescore2 =a2+":"+b2;
                mydb.updateProfile(pl2.getId(), pl2.getNick(),savescore2);
            }

            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        else{
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }
}