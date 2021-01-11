package com.example.number_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class product_View extends AppCompatActivity {
    private TextView textView;
    private Button menu, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales__view);
        setTitle("매출 관리");

        Intent intent = getIntent();
        textView = findViewById(R.id.textView);
        menu = findViewById(R.id.menu_return);
        login = findViewById(R.id.login_return);

        textView.setText(intent.getStringExtra("id").toString()+"님의 상품");

        menu.setOnClickListener(v->{
            finish();
        });

        login.setOnClickListener(v->{
            Intent intent1 = new Intent(product_View.this,MainActivity.class);
            startActivity(intent1);
        });
    }
}