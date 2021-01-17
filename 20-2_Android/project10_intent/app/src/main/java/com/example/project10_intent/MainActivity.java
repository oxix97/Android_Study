package com.example.project10_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button plus;
    EditText editText1, editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 엑티비티");

        plus = (Button)findViewById(R.id.btnNewActivity);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1 = (EditText)findViewById(R.id.Num1);
                editText2 = (EditText)findViewById(R.id.Num2);
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("Edit1",Integer.parseInt(editText1.getText().toString()));
                intent.putExtra("Edit2",Integer.parseInt(editText2.getText().toString()));
                startActivityForResult(intent,0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            int hap  = data.getIntExtra("Hap",0);
            Toast.makeText(getApplicationContext(), "합계: "+hap, Toast.LENGTH_SHORT).show();
        }
    }
}