package com.example.project1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.project1.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private fun textNullChecker(): Boolean {
        if (binding.etName.text.length.toString() == "0" || binding.etId.text.length.toString() == "0" || binding.etPw.text.length.toString() == "0")
            return false
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSignUp.setOnClickListener {
            if (textNullChecker()) {
                val intent = Intent()
                intent.putExtra("setId", binding.etId.text.toString())
                intent.putExtra("setPw", binding.etPw.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}