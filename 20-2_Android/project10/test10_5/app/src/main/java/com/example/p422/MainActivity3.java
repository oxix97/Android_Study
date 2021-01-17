package com.example.p422;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity3 extends AppCompatActivity {
    Button btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("Third 액티비티");
        Intent intent = getIntent();
        btn4 = (Button)findViewById(R.id.button4);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivityForResult(intent,0);
            }
        });
    }
}