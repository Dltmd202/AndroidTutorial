package com.example.samplethread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int value = 0;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundTread thread = new BackgroundTread();
                thread.run();
            }
        });
    }
    //Tread 인터페이스를 확장시킨다.
    class BackgroundTread extends Thread{
        public void run(){
            for (int i = 0 ; i < 100 ; i++ ){
                try{
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                value += 1;
                Log.d("Thread","value : "+value);
                //textView.setText("vlaue :" +value);
            }
        }
    }
}