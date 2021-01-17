package com.example.stop_watch_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends Activity {
    Chronometer chronometer;
    Button startButton, stopButton, resetButton;
    long timeStop = 0;
    boolean clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("스톱워치");
        chronometer = (Chronometer)findViewById(R.id.chronometer_view);
        startButton = (Button)findViewById(R.id.start);
        stopButton = (Button)findViewById(R.id.stop);
        resetButton = (Button)findViewById(R.id.reset);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                int h = (int)(time /3600000);
                int m = (int)(time - h*3600000)/60000;
                int s = (int)(time - h*3600000- m*60000)/1000 ;
                String t = (h < 10 ? "0"+h: h)+ ":" +(m < 10 ? "0"+m: m)+ ":" + (s < 10 ? "0"+s: s);
                chronometer.setText(t);
            }
        });
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setText("00:00:00");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime() + timeStop);
                chronometer.start();
                clicked = false;
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!clicked){
                    timeStop = chronometer.getBase() - SystemClock.elapsedRealtime();
                    chronometer.stop();
                    timeStop = 0;
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                timeStop = 0;
            }
        });
    }
}