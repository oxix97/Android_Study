package com.example.bmi_calulator

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)

        val bmiValue = weight / (height / 100.0).pow(2.0)
        val resultText = when {
            bmiValue >= 30.5 -> "고도 비만"
            bmiValue >= 30.0 -> "중정도 비만"
            bmiValue >= 25.0 -> "경도 비만"
            bmiValue >= 23.0 -> "과체중"
            bmiValue >= 18.5 -> "정상체중"
            else -> "저체중"
        }
        val resultValueTextView = findViewById<TextView>(R.id.BMIResultTextView)
        val resultStringTextView = findViewById<TextView>(R.id.ResultTextView)

        resultValueTextView.text = bmiValue.toString()
        resultStringTextView.text = resultText
    }
}