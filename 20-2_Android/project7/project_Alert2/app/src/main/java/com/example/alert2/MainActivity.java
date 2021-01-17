package com.example.alert2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Alert_Array");
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] versionArray = new String[] {"개구리","병아리","돼지"};
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("좋아하는것은? ");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setSingleChoiceItems(versionArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn.setText(versionArray[which]);
                    }
                });
                dlg.setPositiveButton(" 확인",null);
                dlg.setNegativeButton("취소",null);
                dlg.show();
            }
        });
    }
}