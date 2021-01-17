package com.example.project4_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Switch switchBtn;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3;
    ImageView imageView;
    Button exit, reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ImageView");

        textView = (TextView)findViewById(R.id.textView2);
        switchBtn = (Switch)findViewById(R.id.switch1);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton)findViewById(R.id.radioButton);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton)findViewById(R.id.radioButton3);
        imageView = (ImageView)findViewById(R.id.imageView2);
        exit = (Button)findViewById(R.id.button);
        reset = (Button)findViewById(R.id.button2);

        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switchBtn.isChecked() == true){
                    textView.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                }else{
                    textView.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.INVISIBLE);
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(radioGroup.getCheckedRadioButtonId()){
                    case R.id.radioButton:
                        imageView.setImageResource(R.drawable.wd1);
                        imageView.setVisibility(View.VISIBLE); break;
                    case R.id.radioButton2:
                        imageView.setImageResource(R.drawable.wd2);
                        imageView.setVisibility(View.VISIBLE); break;
                    case R.id.radioButton3:
                        imageView.setImageResource(R.drawable.wd3);
                        imageView.setVisibility(View.VISIBLE); break;
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchBtn.setChecked(false);
                radioButton1.setChecked(false);
                radioButton2.setChecked(false);
                radioButton3.setChecked(false);
                imageView.setVisibility(View.INVISIBLE);
            }
        });
    }
}