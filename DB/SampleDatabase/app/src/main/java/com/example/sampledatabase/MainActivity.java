package com.example.sampledatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    TextView textView;

    SQLiteDatabase database;
    String tableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);

        Button button  = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                createDataBase(name);
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableName = editText2.getText().toString();
                createTable(tableName);
                insertRecord();
            }
        });
    }
    void println(String data){
        Log.d("Main",data);
        textView.append(data);
    }
    private void createDataBase(String name){
        println("Try to create DB");
        database = openOrCreateDatabase(name,MODE_PRIVATE,null);
        println("Success to create : "+name);
    }
    private void createTable(String name){
        println("Try to create Table");

        if(database == null){
            println("Create DataBase First");
            return;
        }
        database.execSQL("create table if not exists " + name +"("
                    + "_id integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " age integer,"
                    + " mobile text)"
        );
        println("Success to create Table");
    }
    private void insertRecord(){
        println("Try to insert");
        if (database == null){
            println("Create DataBase First");
        }
        if (tableName == null) {
            println("Create Table First");
        }

        database.execSQL("insert into "+tableName
                +"(name , age , mobile)"
                +" values"
                +"('John',20,'010-1000-2000')"
        );
        println("Success to insert Record");
    }
}