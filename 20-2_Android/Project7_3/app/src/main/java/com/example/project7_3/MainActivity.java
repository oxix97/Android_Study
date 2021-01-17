package com.example.project7_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvName, tvEmail, toastText;
    Button button;
    EditText editName, editEmail;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사용자 정보 입력1");

        tvName = (TextView)findViewById(R.id.textView);
        tvEmail = (TextView)findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialogView = (View) View.inflate(MainActivity.this,R.layout.dialog1,null);
                AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
                dig.setTitle("사용자 정보 입력2");
                dig.setIcon(R.drawable.star);
                dig.setView(dialogView);
                dig.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editName = (EditText) dialogView.findViewById(R.id.edit1);
                        editEmail = (EditText) dialogView.findViewById(R.id.edit2);

                        tvName.setText(editName.getText().toString());
                        tvEmail.setText(editEmail.getText().toString());
                    }
                });
                dig.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = new Toast(MainActivity.this);
                        toastView = (View)View.inflate(MainActivity.this,R.layout.toast1,null);
                        toastText = (TextView)toastView.findViewById(R.id.textView5);
                        toast.setView(toastView);
                        toast.show();
                    }
                });
                dig.show();
            }
        });
    }
}