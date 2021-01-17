package com.example.scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scroll;
    ImageView img;
    BitmapDrawable bit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Take a picture");

        scroll.findViewById(R.id.scr);
        img.findViewById(R.id.imageView1);
        scroll.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bit = (BitmapDrawable) res.getDrawable(R.drawable.yerim);
        int bitWidth = bit.getIntrinsicWidth();
        int bitHeight = bit.getIntrinsicHeight();

        img.setImageDrawable(bit);
        img.getLayoutParams().width = bitWidth;
        img.getLayoutParams().height = bitHeight;
    }
    public void onButton(View v){
        change();
    }

    private void change() {
        Resources res = getResources();
        bit = (BitmapDrawable) res.getDrawable(R.drawable.yerim2);
        int bitWidth = bit.getIntrinsicWidth();
        int bitHeight = bit.getIntrinsicHeight();

        img.setImageDrawable(bit);
        img.getLayoutParams().width = bitWidth;
        img.getLayoutParams().height = bitHeight;

    }
}