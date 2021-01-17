package com.example.pb4_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
public class MainSeven extends AppCompatActivity{
    CheckBox chk1,chk2,chk3;
    Button btn;
    int togle1,togle2,togle3 =0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven);
        setTitle("연습문제4-7");
        chk1 =(CheckBox)findViewById(R.id.chkbox1);
        chk2 =(CheckBox)findViewById(R.id.chkbox2);
        chk3 =(CheckBox)findViewById(R.id.chkbox3);
        btn = (Button)findViewById(R.id.btn);

        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(togle1 == 0){
                    btn.setEnabled(false);
                    togle1++;
                }
                else{
                    btn.setEnabled(true);
                    togle1--;
                }
            }
        });
        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(togle2 == 0){
                    btn.setClickable(false);
                    togle2++;
                }
                else{
                    btn.setClickable(true);
                    togle2--;
                }
            }
        });
        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(togle3 == 0){
                    btn.setRotation(45);
                    togle3++;
                }
                else{
                    btn.setRotation(0);
                    togle3--;
                }
            }
        });
    }
}
