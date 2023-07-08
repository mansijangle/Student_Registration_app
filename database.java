package com.example.example_database;

import static android.provider.Settings.System.getString;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public static final String dbname = "signup.db";

    public database(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String w = "create table user (_id integer primary key autoincrement, name text, username text , password text)";
        sqLiteDatabase.execSQL(w);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public boolean insert_date(String Name, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("name", Name);
        c.put("username", username);
        c.put("password", password);
        long r = db.insert("user", null, c);
        if (r == -1) return false;
        else
            return true;
    }
    public Cursor getInfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user ",null);
        return cursor;
    }

    public boolean  delete_data(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =  db.rawQuery("select *  from user where username=?",new String[]{username});
        if(cursor.getCount()>0){
            long r  = db.delete("user","username=?", new String[]{username});
            if(r==-1) return false;
            else
                return true;
        }
        return  false;
    }
    public boolean update_data(String Name, String Username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("name",Name);
        c.put("Username",Username);
        c.put("Password",password);
        Cursor cursor = db.rawQuery("select * from user where Username=? ",new String[]{Username});
       if(cursor.getCount()>0){
           long r = db.update("user",c,"Username=?",new String[]{Username});
           if(r==-1) return false;
           else
               return true;
       }
       else{
           return false;
       }
    }

}
