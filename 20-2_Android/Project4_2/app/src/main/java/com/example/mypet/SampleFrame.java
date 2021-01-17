package com.example.mypet;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SampleFrame extends AppCompatActivity {
    ImageView imgView1;
    ImageView imgView2;
    int imgIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sampleframe);
        setTitle("SampleFrameLayout");

        imgView1 = findViewById(R.id.imageView1);
        imgView2 = findViewById(R.id.imageView2);
    }
    public void onClicked(View v){
        changeImage();
    }
    public void changeImage(){
        if(imgIndex==0){
            imgView1.setVisibility(View.VISIBLE);
            imgView2.setVisibility(View.INVISIBLE);
            imgIndex=1;
        }
        else if(imgIndex==1){
            imgView1.setVisibility(View.INVISIBLE);
            imgView2.setVisibility(View.VISIBLE);
            imgIndex=0;
        }
    }
}