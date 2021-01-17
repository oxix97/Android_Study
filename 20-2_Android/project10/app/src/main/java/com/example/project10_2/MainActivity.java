package com.example.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    RadioGroup radioGroup;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("첫번째 화면");

        btn = findViewById(R.id.button);
        radioGroup = findViewById(R.id.RadioGroup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radioButton2:
                        intent = new Intent(getApplicationContext(),MainActivity2.class);
                        startActivity(intent); break;
                    case R.id.radioButton1:
                        intent = new Intent(getApplicationContext(),MainActivity3.class);
                        startActivity(intent); break;
                    default:
                        Toast.makeText(getApplicationContext(),"선택하세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}