package com.example.pb4_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainNine extends AppCompatActivity {
    Button btn1;
    ImageView img;
    int turn = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine);
        setTitle("연습문제 4-9");
        btn1 = (Button) findViewById(R.id.btn1);
        img = (ImageView) findViewById(R.id.img);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn = turn+10;
                img.setRotation(turn);
            }
        });
    }
}