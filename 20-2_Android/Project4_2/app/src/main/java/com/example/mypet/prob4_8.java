package com.example.mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class prob4_8 extends AppCompatActivity{
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toastview);
        editText = findViewById(R.id.editText);
        try{
            Toast toastView = Toast.makeText(this,editText.getText(),Toast.LENGTH_SHORT);
            toastView.show();
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
    }
}
