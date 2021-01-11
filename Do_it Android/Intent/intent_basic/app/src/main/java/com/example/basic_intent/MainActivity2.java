package com.example.basic_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

public class MainActivity2 extends AppCompatActivity {
    private LinearLayout container;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        container = findViewById(R.id.container);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub1,container,true);
                CheckBox checkBox = container.findViewById(R.id.checkbox);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(checkBox.isChecked()){
                            checkBox.setText("확인되었습니다.");
                        }else{
                            checkBox.setText("미확인");
                        }
                    }
                });
            }
        });

    }
}