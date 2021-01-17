package com.example.text7_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    RadioGroup group;
    Button btn;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        group = (RadioGroup)findViewById(R.id.group);
        btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                View dlgView = View.inflate(MainActivity.this,R.layout.animal,null);

                img = (ImageView) dlgView.findViewById(R.id.dog);
                dlg.setView(dlgView);

                switch(group.getCheckedRadioButtonId()){
                    case R.id.radioButton1:
                        dlg.setTitle("강아지");
                        img.setImageResource(R.drawable.dog); break;
                    case R.id.radioButton2:
                        dlg.setTitle("고양이");
                        img.setImageResource(R.drawable.cat); break;
                    case R.id.radioButton3:
                        dlg.setTitle("토끼");
                        img.setImageResource(R.drawable.rabbit); break;
                    case R.id.radioButton4:
                        dlg.setTitle("말");
                        img.setImageResource(R.drawable.horse); break;
                }
                dlg.setPositiveButton("닫기",null);
                dlg.show();
            }
        });

    }

}