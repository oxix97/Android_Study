package com.example.number_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Customer_View extends AppCompatActivity {
    TextView textView;
    Button menu, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__view);
        setTitle("고객 관리");
        Intent intent = getIntent();

        textView = findViewById(R.id.textView);
        menu = findViewById(R.id.menu_return);
        login = findViewById(R.id.login_return);

        textView.setText(intent.getStringExtra("id").toString()+"님의 고객");

        login.setOnClickListener(v->{
            Intent intent1 = new Intent(Customer_View.this,MainActivity.class);
            startActivity(intent1);
        });

        menu.setOnClickListener(v -> {
            finish();
        });


    }
}