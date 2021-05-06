package com.example.bmi_calulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightEditText : EditText = findViewById(R.id.height)
        val weightEditText : EditText = findViewById(R.id.weight)
        val resultButton : Button = findViewById(R.id.button)


    }
}