package com.example.number_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    private TextView textView;
    private Button button1, button2, button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("메인메뉴");

        Intent intent = getIntent();
        textView = findViewById(R.id.textView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        textView.setText(intent.getStringExtra("id").toString()+"님 환영합니다.");

        button1.setOnClickListener(v -> {
            Intent intent1 = new Intent(MenuActivity.this,Customer_View.class);
            String id = intent.getStringExtra("id");
            intent1.putExtra("id",id);
            startActivity(intent1);
        });

        button2.setOnClickListener(v->{
            Intent intent2 = new Intent(MenuActivity.this,Sales_View.class);
            String id = intent.getStringExtra("id");
            intent2.putExtra("id",id);
            startActivity(intent2);
        });

        button3.setOnClickListener(v->{
            Intent intent3 = new Intent(MenuActivity.this,product_View.class);
            String id = intent.getStringExtra("id");
            intent3.putExtra("id",id);
            startActivity(intent3);
        });
    }
}