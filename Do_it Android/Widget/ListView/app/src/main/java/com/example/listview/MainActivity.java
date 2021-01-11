package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ListView");

        listView = findViewById(R.id.listView);

        List<String> data = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);

        data.add("아에이오우");
        data.add("영우디에스피");
        data.add("팬오션");
        adapter.notifyDataSetChanged(); //  저장하는역할
    }
}