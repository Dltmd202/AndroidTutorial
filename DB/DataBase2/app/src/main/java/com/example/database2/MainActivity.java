package com.example.database2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    EditText editText2;

    SQLiteDatabase database;

    DataBaseHelper helper;
    String tableName ="emp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText =findViewById(R.id.editText);
        editText2= findViewById(R.id.editText2);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                createDataBase(name);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertRecord();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeQuery();
            }
        });
    }

    private void createDataBase(String name){
        println("call createDataBase");
        helper = new DataBaseHelper(this);
        database = helper.getWritableDatabase();
        println("Success to createDataBase");
    }

    private void insertRecord(){
        println("Call insertRecord");
        database.execSQL("INSERT INTO emp ( name , age, mobile)"+
                "VALUES ( 'LEE' , 22,'010-1000-1000' )"
                );
    }

    private void executeQuery(){
        println("call executeQuery");

        Cursor cursor = database.rawQuery(" SELECT * FROM emp",null);
        int recordCount = cursor.getCount();
        println("recordCount : " +recordCount );
        for ( int i = 0 ; i < recordCount; i ++ ){
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String mobile = cursor.getString(3);

            println("Record# "+i +" "+id+" "+name+" "+age+ " "+ mobile);
        }
        cursor.close();
    }



    public void println(String data){
        Log.d("Main",data);
        textView.append(data);

    }
}