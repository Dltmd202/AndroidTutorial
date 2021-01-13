package com.example.sampleprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    public String uriString ="content://com.example.sampleprovider/person";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView  = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPerson();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryPerson();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePerson();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePerson();
            }
        });
    }


    public void insertPerson(){
        println("Call insertPerson");
        String uriString ="content://com.example.sampleprovider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);
        //content://com.example.sampleprovider/person 를 호출하여 가져옴
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);

        String[] columns = cursor.getColumnNames();
        println("columns count -> "+columns.length);
        //칼럼 정보들을 보여준다
        for (int i = 0 ; i < columns.length ; i ++){
            println("#"+i+" : "+columns[i]);
        }

        ContentValues values = new ContentValues();
        values.put("name","john");
        values.put("age",20);
        values.put("mobile","010-1000-1000");

        uri = getContentResolver().insert(uri,values);
        println("Result of insert -> "+uri.toString());



        println("Success to insertPerson");
    }

    public void queryPerson(){
        try {
            println("Call insertPerson");
            Uri uri = new Uri.Builder().build().parse(uriString);

            String[] columns = new  String[] {"name","age","mobile"};
            Cursor cursor = getContentResolver().query(uri,columns,null,null,DataBaseHelper.PERSON_NAME+" ASC");
            println("Result of Query -> "+cursor.getCount());
            int index  = 0 ;
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(columns[0]));
                int age = cursor.getInt(cursor.getColumnIndex(columns[1]));
                String mobile = cursor.getString(cursor.getColumnIndex(columns[2]));
                println("#"+index + " "+name +" , "+age+" , "+mobile );
                index += 1;
            }




            println("Success to queryPerson");
        }
        catch (Exception e) {e.printStackTrace();}
    }

    public void deletePerson(){
        println("Call insertPerson");
        Uri uri = new Uri.Builder().build().parse(uriString);

        String selection = "name = ?";
        String[] selectionArgs = new String[] {"john"};

        int count = getContentResolver().delete(uri,selection,selectionArgs);
        println("Result of delete : "+count );

        println("Success to deletePerson");
    }

    public void updatePerson(){
        println("Call insertPerson");
        Uri uri = new Uri.Builder().build().parse(uriString);

        String toDelete = "010-1000-1000";

        String selection = "mobile = ?";
        String[] selectionArgs = new String[] {toDelete};
        ContentValues values = new ContentValues();

        values.put("mobile",toDelete);

        int count = getContentResolver().update(uri,values,selection,selectionArgs);
        println("Result of updata : "+count);

        println("Success to updatePerson");
    }






















    void println(String data){
        Log.d("Main",data);
        textView.append(data+"\n");
    }
}