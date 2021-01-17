package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2;
    Button bAdd,bSub,bMul,bDiv,bRset;
    TextView text;
    String num1, num2;
    Integer result;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = {R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,
            R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9};
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("직접풀어보기 과제 5-5");

        edt1 = (EditText) findViewById(R.id.et1);
        edt2 = (EditText) findViewById(R.id.et2);
        bAdd = (Button) findViewById(R.id.Add);
        bSub = (Button) findViewById(R.id.Sub);
        bMul = (Button) findViewById(R.id.Mul);
        bDiv = (Button) findViewById(R.id.Div);
        bRset = (Button) findViewById(R.id.Reset);
        text = (TextView)findViewById(R.id.textView2);

        bAdd.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edt1.getText().toString();
                num2 = edt2.getText().toString();
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                text.setText("계산결과: "+result.toString());
                return false;
            }
        });
        bSub.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edt1.getText().toString();
                num2 = edt2.getText().toString();
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                text.setText("계산결과: "+result.toString());
                return false;
            }
        });
        bMul.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edt1.getText().toString();
                num2 = edt2.getText().toString();
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                text.setText("계산결과: "+result.toString());
                return false;
            }
        });
        bDiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edt1.getText().toString();
                num2 = edt2.getText().toString();
                result = Integer.parseInt(num1) / Integer.parseInt(num2);
                text.setText("계산결과: "+result.toString());
                return false;
            }
        });
        bRset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                edt1.setText("");
                edt2.setText("");
                result=0;
            }
        });
        for(i=0;i<numBtnIDs.length;i++){
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }
        for(i=0;i<numBtnIDs.length;i++){
            final int index;
            index = i;
            numButtons[index].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(edt1.isFocused()==true){
                        num1 = edt1.getText().toString() + numButtons[index].getText().toString();
                        edt1.setText(num1);
                    }else if(edt2.isFocused()==true){
                        num2 = edt2.getText().toString() + numButtons[index].getText().toString();
                        edt2.setText(num2);
                    }else{
                        Toast.makeText(getApplicationContext(),"먼저 에디트텍스트를 선택하세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}