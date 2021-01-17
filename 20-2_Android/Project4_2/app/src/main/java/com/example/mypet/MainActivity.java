package com.example.mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button b1,b2;
    RadioButton r1,r2;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("좀 그럴듯한 앱");
        editText = (EditText) findViewById(R.id.editText);
        b1 = (Button) findViewById(R.id.button3);
        b2 = (Button) findViewById(R.id.button4);
        r1 = (RadioButton) findViewById(R.id.radioButton1);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        img = (ImageView) findViewById(R.id.imageView);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), editText.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(editText.getText())));
                startActivity(intent);
            }
        });

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.icecs);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.jin);
            }
        });
    }
}