package com.example.ratingbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {
    Button plus, minus;
    RatingBar ratingBar1, ratingBar2, ratingBar3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("레이팅 바");

        ratingBar1 = findViewById(R.id.ratingBar1);
        ratingBar2 = findViewById(R.id.ratingBar2);
        ratingBar3 = findViewById(R.id.ratingBar3);
        plus = findViewById(R.id.button);
        minus = findViewById(R.id.button2);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar1.setRating(ratingBar1.getRating() + ratingBar1.getStepSize());
                ratingBar2.setRating(ratingBar2.getRating() + ratingBar2.getStepSize());
                ratingBar3.setRating(ratingBar3.getRating() + ratingBar3.getStepSize());
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar1.setRating(ratingBar1.getRating() - ratingBar1.getStepSize());
                ratingBar2.setRating(ratingBar2.getRating() - ratingBar2.getStepSize());
                ratingBar3.setRating(ratingBar3.getRating() - ratingBar3.getStepSize());
            }
        });
    }
}