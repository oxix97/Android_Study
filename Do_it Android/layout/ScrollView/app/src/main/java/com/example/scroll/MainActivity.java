package com.example.scroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    Button button;
    BitmapDrawable bitmapDrawable;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ScrollView");

        //객체 참조
        scrollView = findViewById(R.id.scroll);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        scrollView.setHorizontalScrollBarEnabled(true);

        //리소스 이미지 참조
        Resources resources = getResources();
        bitmapDrawable = (BitmapDrawable) resources.getDrawable(R.drawable.pinggae);
        int bitmapWidth = bitmapDrawable.getIntrinsicWidth();
        int bitmapHeight = bitmapDrawable.getIntrinsicHeight();

        //이미지 크기 설정
        imageView.setImageDrawable(bitmapDrawable);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;

    }
    public void onClick(View view){
        changeImage();
    }
    private void changeImage(){
        Resources resources = getResources();
        bitmapDrawable = (BitmapDrawable) resources.getDrawable(R.drawable.wd);
        int bitmapWidth = bitmapDrawable.getIntrinsicWidth();
        int bitmapHeight = bitmapDrawable.getIntrinsicHeight();
        imageView.setImageDrawable(bitmapDrawable);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }
}