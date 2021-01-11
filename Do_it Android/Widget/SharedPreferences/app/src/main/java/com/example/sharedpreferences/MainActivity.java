package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    String shared = "File";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit);

        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        String value = sharedPreferences.getString("Chan","");
        editText.setText(value);
    }

    @Override
    protected void onDestroy() { // 액티비티를 벗어난경우 실행이 됨
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = editText.getText().toString();
        editor.putString("Chan",value);
        editor.commit();
    }
}