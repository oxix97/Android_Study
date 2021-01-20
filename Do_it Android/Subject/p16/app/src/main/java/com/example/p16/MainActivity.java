package com.example.p16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private Button move, back;
    private EditText editText;
    private WebView webView;
    private LinearLayout webLayout,menu;
    private Boolean isOpen = false;
    Animation left, right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("웹브라우저 화면 구성");

        menu = findViewById(R.id.menuLayout);
        webLayout = findViewById(R.id.webLayout);
        editText = findViewById(R.id.address);
        move = findViewById(R.id.move);
        webView = findViewById(R.id.webView);
        back = findViewById(R.id.back);

        left = AnimationUtils.loadAnimation(this,R.anim.open_animation);
        right =AnimationUtils.loadAnimation(this,R.anim.close_animation);

        SlideAnimation listener = new SlideAnimation();
        left.setAnimationListener(listener);
        right.setAnimationListener(listener);

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                webView.loadUrl(url);
                webLayout.startAnimation(left);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webLayout.startAnimation(right);
            }
        });

    }

    class SlideAnimation implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            if(isOpen){
                webLayout.setVisibility(View.GONE);
                isOpen = false;
            }else{
                webLayout.setVisibility(View.VISIBLE);
                isOpen = true;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}