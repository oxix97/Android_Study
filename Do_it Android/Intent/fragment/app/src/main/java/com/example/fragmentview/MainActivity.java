package com.example.fragmentview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    BlankFragment blankFragment;
    MenuFragment menuFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Fragment_main");
    }

    public void onFragmentChanged(int i) {
        if(i == 0){
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.container,menuFragment).commit();
        }else if(i == 1){
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.container,blankFragment).commit();
        }
    }
}