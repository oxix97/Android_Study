package com.example.recycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recylerView);

         LinearLayoutManager manager =
               new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        //한줄당 4개 나옴
       // GridLayoutManager manager = new GridLayoutManager(this,4); //한줄당 4개 나옴
        recyclerView.setLayoutManager(manager);

        PersonAdapter adapter = new PersonAdapter();
        adapter.addItem(new Person("이종찬","010-6206-1416"));
        adapter.addItem(new Person("권시현","010-6206-1416"));
        adapter.addItem(new Person("이이익","010-6206-1416"));
        adapter.addItem(new Person("잉이익","010-6206-1416"));

        recyclerView.setAdapter(adapter);

    }
}