package com.example.hul0049_tamz2_lode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hul0049_tamz2_lode.Classes.DBHelper;
import com.example.hul0049_tamz2_lode.Classes.Player;
import com.example.hul0049_tamz2_lode.Classes.ProfileAdapter;

import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {

    DBHelper mydb;
    ListView itemListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //ArrayList<String> arrayList = new ArrayList<String>();
        //arrayList = mydb.getProfileList();
        //ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);

        mydb = new DBHelper(this);
        ArrayList<Player> arrayOfUsers = new ArrayList<Player>();
        arrayOfUsers = mydb.getProfileList();
        ProfileAdapter profadapt = new ProfileAdapter(this, arrayOfUsers);

        itemListView = (ListView) findViewById(R.id.listView1);
        itemListView.setAdapter(profadapt);

      //  itemListView = findViewById(R.id.listView1);
     //   itemListView.setAdapter(arrayAdapter);
       itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Player player = (Player)itemListView.getItemAtPosition(position);

                //Toast.makeText(getApplicationContext()," " + myItem,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),DisplayItemActivity.class);
                intent.putExtra("id",player.getId());
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_menu_profiles)
        {
            Intent intent = new Intent(getApplicationContext(), DisplayItemActivity.class);
            intent.putExtra("id",0);
            startActivity(intent);
            finish();
        }

        if (id == R.id.del_menu_profiles)
        {
            int nRowDeleted = mydb.removeAll();
            Toast.makeText(this, "Deleted records: " + nRowDeleted, Toast.LENGTH_SHORT).show();
            recreate();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "onBackPressed", Toast.LENGTH_SHORT).show();
    }

}
