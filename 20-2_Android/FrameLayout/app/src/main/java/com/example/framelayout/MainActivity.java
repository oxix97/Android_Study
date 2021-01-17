package com.example.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img1;
    ImageView img2;
    Button btn;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("YeRim_Jung");

        img1 = findViewById(R.id.imageView1);
        img2 = findViewById(R.id.imageView2);
    }
    public void onClick(View v){
        change();
    }
    public void change(){
        if(index==1){
            img1.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.VISIBLE);
            index=0;

        }
        else{
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.INVISIBLE);
            index=1;
        }
    }
}