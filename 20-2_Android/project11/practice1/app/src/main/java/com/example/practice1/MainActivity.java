package com.example.practice1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("리스트뷰 테스트");

        final String[] MID = {"히어로즈","24시","로스트","스몰빌","탐정몽크","빅뱅이론","프렌즈",
        "덱스터","글리","테이큰","슈퍼내추럴","브이"}; // 나중에 배열을 찾을때 없으면 안되어서 변수가 아닌 상수로 선언

        ListView list = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,MID); // layout 파일 이름 -> simple_list_item
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), MID[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}