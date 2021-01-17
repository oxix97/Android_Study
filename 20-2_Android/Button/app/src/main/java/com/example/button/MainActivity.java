package com.example.button;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageButton btn;
    ImageView img;
    int cont = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ImageButton");
        setContentView(R.layout.edit_text_main);
        btn = (ImageButton) findViewById(R.id.imageButton);
        img = (ImageView)findViewById(R.id.imageView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cont==0){
                    img.setVisibility(View.VISIBLE);
                    cont=1;
                }
                else{
                    img.setVisibility(View.INVISIBLE);
                    cont=0;
                }
            }
        });
    }
}