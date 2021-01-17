package com.example.project7_1java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button  btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Java 7_1");

        baseLayout = (LinearLayout)findViewById(R.id.layout);
        btn = (Button)findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0,1,0,"RED");
        menu.add(0,2,0,"MEGENTA");
        menu.add(0,3,0,"YELLOW");

        SubMenu subMenu = menu.addSubMenu("ButtonSetting");
        subMenu.add(0,4,0,"45 rotate");
        subMenu.add(0,5,0,"DoubleSize");

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case 1:
                baseLayout.setBackgroundColor(Color.RED); return true;
            case 2:
                baseLayout.setBackgroundColor(Color.MAGENTA); return  true;
            case 3:
                baseLayout.setBackgroundColor(Color.YELLOW); return true;
            case 5:
                btn.setScaleY(2);
                btn.setScaleX(2); return true;
            case 4:
                btn.setRotation(45); return true;
        }
        return false;
    }
}