package com.example.project10_intent;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("두번째 액티비티");

        Intent inIntent = getIntent();
        final int hapValue = inIntent.getIntExtra("Edit1",0)
                + inIntent.getIntExtra("Edit2",0);
        Button reBtn = (Button)findViewById(R.id.Rebutton);
        reBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);
                outIntent.putExtra("Hap",hapValue);
                setResult(RESULT_OK,outIntent);
                finish();
            }
        });
    }
}
