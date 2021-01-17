package com.example.dynamic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Dynamic List");

        final ArrayList<String> midList = new ArrayList<>();
        ListView list = findViewById(R.id.listView);
        Button btn = findViewById(R.id.button);
        EditText editText = findViewById(R.id.edit1);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,midList);
        list.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                midList.add(editText.getText().toString());
                adapter.notifyDataSetChanged(); // -> 리스트 새로고침
            }
        });
        /*list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View view, int position, long id) {
                midList.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        */
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                midList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}