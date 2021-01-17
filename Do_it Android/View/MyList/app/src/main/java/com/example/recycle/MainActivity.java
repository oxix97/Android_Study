package com.example.recycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    PersonAdapter adapter;
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

        adapter = new PersonAdapter();
        adapter.addItem(new Person("이종찬","010-6206-1416"));
        adapter.addItem(new Person("권시현","010-6206-1416"));
        adapter.addItem(new Person("이이익","010-6206-1416"));
        adapter.addItem(new Person("잉이익","010-6206-1416"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickedListener(new OnPersonItemClickedListener() {
            @Override
            public void onItemClicked(PersonAdapter.ViewHolder holder, View view, int position) {
                Person item = adapter.getItem(position);
                showToast("아이템 선택됨 : "+item.getName());
            }
        });
    }

    private void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}