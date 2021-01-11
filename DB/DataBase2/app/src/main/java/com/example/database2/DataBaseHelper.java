package com.example.database2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static String NAME = "employee.db";
    public static int VERSION = 1;

    public DataBaseHelper(Context context){
        super(context,NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        println("call onCreate");
        String sql = "CREATE TABLE IF NOT EXISTS emp ( "
                + " _id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + " name TEXT NOT NULL ,"
                + " age INTEGER NOT NULL ,"
                + " mobile TEXT NOT NULL)";
        db.execSQL(sql);
    }

    public void onOpen(SQLiteDatabase db){
        println("call onOpen");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("call onUpgrade");

        if (newVersion > 1){
            db.execSQL("DROP TABLE IF EXISTS emp");
        }
    }

    public void println(String data){
        Log.d("DataBaseHelper",data);
    }
}
