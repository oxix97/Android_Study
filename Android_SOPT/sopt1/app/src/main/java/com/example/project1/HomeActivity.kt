package com.example.project1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.project1.databinding.ActivityMain2Binding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibGithubButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/oxix97/Android_SOPT"))
            startActivity(intent)
        }
    }
}