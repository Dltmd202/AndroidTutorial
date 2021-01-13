package com.example.sampleprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.IllformedLocaleException;

public class PersonProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.sampleprovider";
    private static final String BASE_PATH = "person";
    private static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+BASE_PATH);

    private static final int PERSONS = 1;
    private static final int PERSON_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        uriMatcher.addURI(AUTHORITY,BASE_PATH,PERSONS);
        uriMatcher.addURI(AUTHORITY,BASE_PATH+"/#",PERSON_ID);
    }

    SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        println("call onCreate ");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        database = dataBaseHelper.getWritableDatabase();
        println("DataBase Created");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        println("call query : "+uri.toString());
        Cursor cursor;
        int id = uriMatcher.match(uri);
        println("uri id : "+id);
        switch (id){
            case PERSONS:
                cursor = database.query(DataBaseHelper.TABLE_NAME,DataBaseHelper.ALL_COLUMNS,null,null,
                        null,null,DataBaseHelper.PERSON_NAME+" ASC",null);
                break;
            default:
                println("Content URI : "+CONTENT_URI.toString());
                println("URI Error : "+uri.toString());
                throw new IllegalArgumentException("Unknown URI : "+uri  );
        }

        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        println("Success query with uri : "+uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        println("call getType : "+uri.toString());
        switch (uriMatcher.match(uri)){
            case PERSONS:
                println("Success getType with uri : "+uri.toString());
                return "vnd.android.cursor.dir/persons";
            default:
                println("URI Error");
                throw new IllegalArgumentException("Unknown Uri : "+uri.toString() );
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        println("Call insert : "+uri.toString());
        long id = database.insert(DataBaseHelper.TABLE_NAME,null,values);
        println("Inserted id : "+ id);
        if (id > 0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI,id);
            getContext().getContentResolver().notifyChange(_uri,null);
            println("Success insert with uri : "+uri.toString());
            println("Changed uri : "+_uri.toString());
            return _uri;
        }
        println("URI Error");
        throw new IllegalArgumentException("Unknown uri : "+uri.toString());
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        println("Call delete : "+uri.toString());
        int count = 0 ;
        switch (uriMatcher.match(uri)){
            case PERSONS:
                count = database.delete(DataBaseHelper.TABLE_NAME,selection,selectionArgs);
                println("Delete "+count );
                break;
            default:
                println("URI Error");
                throw new IllegalArgumentException("Unknown uri : "+uri.toString());
        }
        getContext().getContentResolver().notifyChange(uri,null);
        println("Success delete : "+uri.toString());
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PERSONS:
                count = database.update(DataBaseHelper.TABLE_NAME,values,selection,selectionArgs);
                println("Update " +count);
                break;
            default:
                println("URI ERROR");
                throw new IllegalArgumentException("Unknown URI : "+uri.toString());
        }
        return count;
    }
    public void println(String data){
        Log.d("ContentProvider",data);
    }
}
