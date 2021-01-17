package com.example.p310;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView name,email,toastext;
    Button btn;
    EditText editText1, editText2;
    View dialogView, toastView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("p310");

        name = (TextView)findViewById(R.id.textView);
        email = (TextView)findViewById(R.id.textView2);
        btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = (View)View.inflate(MainActivity.this,R.layout.dialog1,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editText1 = (EditText)dialogView.findViewById(R.id.edit1);
                        editText2 = (EditText)dialogView.findViewById(R.id.edit2);

                        name.setText(editText1.getText().toString());
                        email.setText(editText2.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = new Toast(MainActivity.this);
                        toastView =(View)View.inflate(MainActivity.this,R.layout.toast1,null);
                        toastext = (TextView)toastView.findViewById(R.id.toastView);
                        toastext.setText("취소했습니다.");
                        toast.setView(toastView);
                        toast.show();
                    }
                });
                dlg.show();
            }
        });

    }
}