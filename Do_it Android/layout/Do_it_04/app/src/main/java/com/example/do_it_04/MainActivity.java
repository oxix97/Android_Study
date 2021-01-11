package com.example.do_it_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button access, cancel;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Do_it_04");

        access = findViewById(R.id.button3);
        cancel = findViewById(R.id.button4);
        editText = findViewById(R.id.edit);
        textView = findViewById(R.id.textView);

        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().length() > 400){
                    Toast.makeText(getApplicationContext(), "용량을 초과하였습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    textView.setText(editText.getText().length()+"/400");
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}