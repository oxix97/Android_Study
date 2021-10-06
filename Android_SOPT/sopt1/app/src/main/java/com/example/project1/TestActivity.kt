package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project1.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        binding = ActivityTestBinding.inflate(layoutInflater)

    }
}