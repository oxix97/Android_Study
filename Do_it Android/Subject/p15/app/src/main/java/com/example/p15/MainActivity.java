package com.example.p15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private Button move,login;
    private Animation open, close;
    private boolean isOpen = false;
    private LinearLayout loginLayout,buttonLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("입력 화면의 애니메이션");

        open = AnimationUtils.loadAnimation(this,R.anim.open_animation);
        close = AnimationUtils.loadAnimation(this,R.anim.close_animation);

        SlideingAnimationListener listener = new SlideingAnimationListener();
        open.setAnimationListener(listener);
        close.setAnimationListener(listener);

        buttonLayout = findViewById(R.id.layout);
        loginLayout = findViewById(R.id.Login);

        move = findViewById(R.id.move);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    loginLayout.startAnimation(open);
                }else{
                    loginLayout.setVisibility(View.VISIBLE);
                    loginLayout.startAnimation(close);
                }
            }
        });

        login = findViewById(R.id.save);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLayout.setVisibility(View.GONE);
                loginLayout.startAnimation(close);
            }
        });

    }
    class SlideingAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            if(isOpen){
                loginLayout.setVisibility(View.VISIBLE);
                buttonLayout.setVisibility(View.GONE);
                isOpen = false;
            }else{
                loginLayout.setVisibility(View.GONE);
                buttonLayout.setVisibility(View.VISIBLE);
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