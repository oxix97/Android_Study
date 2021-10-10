package com.example.project1
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.project1.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)
        binding.tvTitle.text = "SOPTHub"
        binding.tvNameTitle.text = "이름"
    }
}