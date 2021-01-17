package com.example.project7_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ChangeBackgroundColor");
        baseLayout = (LinearLayout)findViewById(R.id.layout);
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseLayout.setBackgroundColor(Color.WHITE);
                btn.setScaleX(1);
                btn.setScaleY(1);
                btn.setRotation(0);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.RED); return true;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.BLUE); return true;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.GREEN); return  true;
            case R.id.subRotate:
                btn.setRotation(45); return true;
            case R.id.subSize:
                btn.setScaleX(2);
                btn.setScaleY(2); return true;
        }
        return false;
    }
}