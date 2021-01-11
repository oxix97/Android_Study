package com.example.parcelable_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        println("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        println("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        println("onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        println("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        println("onResume");
    }

    public void println(String data){
        Toast.makeText(this,data, Toast.LENGTH_SHORT).show();
        Log.d("Main",data);
    }
}