package com.example.chapter5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chapter5.databinding.ActivityMain2Binding
import kotlin.concurrent.thread

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        binding.tvText.text = intent.extras?.get("title").toString()
        binding.tvNumber.text = intent.getIntExtra("number", 0).toString()
        binding.btnClose.text = intent.extras?.get("value").toString()


        setContentView(binding.root)
        returnIntent()
    }

    private fun returnIntent() {
        binding.btnClose.setOnClickListener {
            val intent = Intent()
            intent.putExtra("ok", "return ok")
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}