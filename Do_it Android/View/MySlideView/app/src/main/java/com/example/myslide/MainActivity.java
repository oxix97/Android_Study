package com.example.myslide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Animation left,right;
    LinearLayout page;
    Button button;
    private boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);
        left = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        right = AnimationUtils.loadAnimation(this,R.anim.translate_right);
        button = findViewById(R.id.button);

        SlideingAnimationListener listener = new SlideingAnimationListener();
        left.setAnimationListener(listener);
        right.setAnimationListener(listener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    page.startAnimation(right);
                }else{
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(left);
                }
            }
        });
    }

    class SlideingAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isOpen){
                page.setVisibility(View.INVISIBLE);
                button.setText("열기");
                isOpen = false;
            }else{
                button.setText("닫기");
                isOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}