package com.example.practice2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    Button btn1, btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Practice2");

        linearLayout = (LinearLayout)findViewById(R.id.layout);
        btn1 = (Button)findViewById(R.id.button);
        btn2 = (Button)findViewById(R.id.button2);

        registerForContextMenu(btn1);
        registerForContextMenu(btn2);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        if(v==btn1){
            menuInflater.inflate(R.menu.menu1,menu);
        }
        if(v==btn2){
            menuInflater.inflate(R.menu.menu2,menu);
        }
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        switch(item.getItemId()){
            case R.id.red:
                linearLayout.setBackgroundColor(Color.RED); return true;
            case R.id.blue:
                linearLayout.setBackgroundColor(Color.BLUE); return true;
            case R.id.magenta:
                linearLayout.setBackgroundColor(Color.MAGENTA); return true;
            case R.id.rotate:
                btn2.setRotation(45); return true;
            case R.id.scale:
                btn2.setScaleX(3);
                btn2.setScaleY(2);
                return true;
            case R.id.reset:
                btn2.setScaleX(1);
                btn2.setScaleY(1);
                btn2.setRotation(0); return true;
        }
        return false;
    }
}