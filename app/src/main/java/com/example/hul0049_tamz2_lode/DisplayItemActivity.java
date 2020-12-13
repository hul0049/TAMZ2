package com.example.hul0049_tamz2_lode;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hul0049_tamz2_lode.Classes.DBHelper;
import com.example.hul0049_tamz2_lode.Classes.Player;

public class DisplayItemActivity extends AppCompatActivity {

    private DBHelper mydb;
    TextView nickTextView;
    int idToUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profiles);

        nickTextView = findViewById(R.id.editTextNick);
        mydb = new DBHelper(this);
        Intent i = getIntent();
        if(i !=null)
        {

            int value = i.getIntExtra("id", 0);
            idToUpdate = value;
            if (idToUpdate > 0)
            {

                Player player = mydb.getData(idToUpdate);

                Button b = findViewById(R.id.buttonSave);
                b.setVisibility(View.INVISIBLE);

                nickTextView.setText(player.getNick());
                nickTextView.setEnabled(false);
                nickTextView.setFocusable(false);
                nickTextView.setClickable(false);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(idToUpdate>0){
            getMenuInflater().inflate(R.menu.display_profiles_menu, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Edit_Contact) {
            Button b = findViewById(R.id.buttonSave);
            b.setVisibility(View.VISIBLE);

            nickTextView.setEnabled(true);
            nickTextView.setFocusableInTouchMode(true);
            nickTextView.setClickable(true);

        }
        if (id == R.id.Delete_Contact)
        {
            mydb.deleteProfile(idToUpdate);
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(i);
        }

        return true;
    }

    public void saveButtonAction(View view)
    {

        if(idToUpdate > 0){
            mydb.updateProfile(idToUpdate, nickTextView.getText().toString(),getScore());
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(i);
        }
        else{
            //vlozeni zaznamu
            if(mydb.insertProfile(nickTextView.getText().toString(),getScore())){
                Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
            }

            else{
                Toast.makeText(getApplicationContext(), "not saved", Toast.LENGTH_SHORT).show();
            }
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(i);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(i);
    }
    public String getScore()
    {
        if(idToUpdate>0)
        {
            Player player = mydb.getData(idToUpdate);

            return player.getScore();
        }
        else
            return "0:0";
    }
}