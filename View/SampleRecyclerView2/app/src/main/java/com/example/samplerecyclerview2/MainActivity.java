package com.example.samplerecyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2,RecyclerView.VERTICAL,false);

        Adapter adapter = new Adapter();


        for(int i = 0 ; i < 10 ; i ++){
            String name = "00"+(i+1);
            String mobile = "010-"+(i+1)+"000-"+(i+1)+"000";
            adapter.items.add(new Person(name,mobile));
        }

        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(Adapter.ViewHolder holder, View view, int position) {
                Person item = adapter.items.get(position);
                Toast.makeText(getApplicationContext(),item.getName()+" "+item.getMobile(),Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



    }
}