package com.example.hul0049_tamz2_lode.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{

        public static final String DATABASE_NAME = "DBProfiles.db";
        public static final String PROFILE_COLUMN_NICK = "nick";
        public static final String PROFILE_COLUMN_SCORE = "score";

        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE profiles " + "(id INTEGER PRIMARY KEY, nick TEXT, score TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS profiles");
            onCreate(db);
        }

        public boolean insertProfile(String nick, String score)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nick", nick);
            contentValues.put("score",score);
            long insertedId = db.insert("profiles", null, contentValues);
            if (insertedId == -1) return false;
            return true;
        }

        public boolean deleteProfile(int id)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM profiles WHERE ID =" + id);
            return true;
        }

        public Player getData(int id){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res =  db.rawQuery("select * from profiles where id=" + id + "", null);
            res.moveToFirst();
            Player player=null;
            ArrayList<Player> arrayList= new ArrayList<>();
            while(res.isAfterLast() == false) {
                int profileid = res.getInt(res.getColumnIndex("id"));
                String nick = res.getString(res.getColumnIndex("nick"));
                String score = res.getString(res.getColumnIndex("score"));

                player = new Player(id,nick,score);

                arrayList.add(player);
                res.moveToNext();
            }

            return player;
        }


        public boolean updateProfile(Integer id, String nick, String score)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("UPDATE profiles SET nick = '" + nick + "', score = '" + score + "' WHERE id = " + id);
            return true;
        }

        public ArrayList<Player> getProfileList()
        {
            ArrayList<Player> arrayList = new ArrayList<Player>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res =  db.rawQuery( "select * from profiles", null );
            res.moveToFirst();

            while(res.isAfterLast() == false){

                String nick = res.getString(res.getColumnIndex(PROFILE_COLUMN_NICK));
                String score = res.getString(res.getColumnIndex(PROFILE_COLUMN_SCORE));
                int id = res.getInt(0);
                Player player = new Player(id,nick,score);
                arrayList.add(player);

                res.moveToNext();
            }

            return arrayList;
        }
    public ArrayList<Player> getProfileListWithOutPlayer(Player pl)
    {
        ArrayList<Player> arrayList = new ArrayList<Player>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from profiles", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            String nick = res.getString(res.getColumnIndex(PROFILE_COLUMN_NICK));
            String score = res.getString(res.getColumnIndex(PROFILE_COLUMN_SCORE));
            int id = res.getInt(0);
            if(pl.getId()!=id)
            {
                Player player = new Player(id,nick,score);
                arrayList.add(player);
            }

            res.moveToNext();
        }

        return arrayList;
    }

        public int removeAll()
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("Delete FROM profiles");

            int nRecordDeleted = 0;
            return nRecordDeleted;
        }


    }


