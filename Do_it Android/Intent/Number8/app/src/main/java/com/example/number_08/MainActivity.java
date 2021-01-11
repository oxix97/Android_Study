package com.example.number_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button login_button;
    private EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_button = findViewById(R.id.login);
        editText1 = findViewById(R.id.edit1);
        editText2 = findViewById(R.id.edit2);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                String id = editText1.getText().toString();
                String pwd = editText2.getText().toString();
                intent.putExtra("id",id);
                intent.putExtra("pwd",pwd);
                Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }
}