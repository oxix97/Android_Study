package com.example.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    Button btn;
    Intent intent;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("세번째 화면");

        intent = getIntent();
        btn = findViewById(R.id.button3);
        radioGroup = findViewById(R.id.radioGG);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(radioGroup.getCheckedRadioButtonId()){
                    case R.id.radioButton:
                        intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent); break;
                    case R.id.radioButton5:
                        intent = new Intent(getApplicationContext(),MainActivity2.class);
                        startActivity(intent); break;
                    default:
                        Toast.makeText(getApplicationContext(),"선택하세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}