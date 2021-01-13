package com.example.sampleprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "person.db";
    private static final  int DATABASE_VERSION = 1;

    public static final String TABLE_NAME ="person";
    public static final String PERSON_ID ="_id";
    public static final String PERSON_NAME ="name";
    public static final String PERSON_AGE ="age";
    public static final String PERSON_MOBILE ="mobile";

    public static final String[] ALL_COLUMNS = new String[] {PERSON_ID,PERSON_NAME,PERSON_AGE,PERSON_MOBILE};

    public static final String CREATE_DATABASE  = "CREATE TABLE "+TABLE_NAME +" ("
            + PERSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
            + PERSON_NAME + " VARCHAR NOT NULL ,"
            + PERSON_AGE + " INTEGER NOT NULL ,"
            + PERSON_MOBILE + " VARCHAR NOT NULL )";



    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME );
        onCreate(db);
    }
}
