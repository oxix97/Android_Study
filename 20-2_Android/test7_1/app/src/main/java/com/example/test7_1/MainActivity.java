package com.example.test7_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    CalendarView cal;
    Button cancel,access;
    int cy,cm,cd;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");

        text = (TextView)findViewById(R.id.title);
        cal = (CalendarView)findViewById(R.id.calendarView);
        cancel = (Button)findViewById(R.id.cancel);
        access = (Button)findViewById(R.id.success);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int d) {
                cy = y;
                cm = m;
                cd = d;
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("날짜를 선택하세요!");
            }
        });
        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = cy+"/"+(cm+1)+"/"+cd;
                text.setText(date);
            }
        });

    }
}