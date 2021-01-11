package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MainFragment mainFragment;
    menuFragment menuFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        menuFragment = new menuFragment();

    }
    public void onFragmentChanged(int index) {
        if(index == 0){
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.container,mainFragment).commit();
        }else if(index == 1){
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.container,menuFragment).commit();
        }
    }
}