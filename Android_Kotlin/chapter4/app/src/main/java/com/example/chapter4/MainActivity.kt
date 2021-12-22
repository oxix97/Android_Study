package com.example.chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.chapter4.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        thread(true) {
            Thread.sleep(3000)
            runOnUiThread {
                showProgressBar(false)
            }
        }
    }

    private fun showProgressBar(show: Boolean) {
        if (show) binding.pbBar.visibility = View.VISIBLE
        else {
            binding.pbBar.visibility = View.GONE
            binding.tvText.visibility = View.GONE
        }
    }


}