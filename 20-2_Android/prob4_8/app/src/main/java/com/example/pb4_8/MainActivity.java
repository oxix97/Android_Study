package com.example.pb4_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 4-8");
        editText =  (EditText)findViewById(R.id.editTextTextPersonName);
        editText.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                result = editText.getText().toString();
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}