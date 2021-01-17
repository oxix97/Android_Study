package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn1 = findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast msg = Toast.makeText(getApplicationContext(),"토스트 연습",Toast.LENGTH_SHORT);
                Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                int xOffset = (int)(Math.random() * display.getWidth());
                int yOffset = (int)(Math.random() * display.getHeight());

                msg.setGravity(Gravity.TOP | Gravity.LEFT,xOffset,yOffset);
                msg.show();
            }
        });
    }
}