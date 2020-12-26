package com.example.samplethread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    MainHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("why....");
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });
        handler = new MainHandler();
    }
    class BackgroundThread extends Thread{
        int value = 0;

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100 ; i++){
                try{
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                value += 1;
                Log.d("Thread","value : "+value);
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value",value);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }
    }
    class MainHandler extends Handler{
        final TextView textView = findViewById(R.id.textview);
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            Log.d("Fuck",Integer.toString(value));
        }
    }




}