package com.example.sacontracts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    public static final int CONTRACT_REQUEST_CODE = 105;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseContract();
            }
        });
    }



    public void println(String data){
        Log.d("Main",data);
        textView.append(data+"\n");
    }

    public void chooseContract(){
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent ,CONTRACT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTRACT_REQUEST_CODE && resultCode == RESULT_OK){
            try{
                Uri uri = data.getData();
                String id = uri.getLastPathSegment();
                println("ID : "+id);
                getContract(id);
            }
            catch (Exception e ){ e.printStackTrace();}
        }
    }

    public void getContract(String id){
        Cursor cursor;
        try{
            cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,ContactsContract.Data.CONTACT_ID,new String[] {id},null);
            if(cursor.moveToFirst()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                println(name);

                String[] columns = cursor.getColumnNames();
                for (String column : columns){
                    int idx = cursor.getColumnIndex(column);
                    String output = ("#"+idx + " "+column+" : "+cursor.getString(idx));
                    println(output);
                }
                cursor.close();
            }
        }
        catch (Exception e) { e.printStackTrace();  }
    }
}












