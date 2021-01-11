package com.example.inflate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    private TextView textView;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = findViewById(R.id.textView);

        Intent intent = getIntent(); // intent 택배 수신
        str = intent.getStringExtra("str"); // 발신자 누구인지 보고 수신

        textView.setText(str);

    }
}