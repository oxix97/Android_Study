package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.project1.databinding.ActivityMainBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val signUpIntent = Intent(this@SignInActivity, SignUpActivity::class.java)
        val longIntent = Intent(this@SignInActivity, HomeActivity::class.java)

        getResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK) {
                val id = it.data?.getStringExtra("setId")
                val pw = it.data?.getStringExtra("setPw")
                binding.idEditText.setText(id)
                binding.pwEditText.setText(pw)
            }
        }
        binding.login.setOnClickListener {
            if (textNullChecker()) {
                Toast.makeText(this, binding.idEditText.text, Toast.LENGTH_SHORT).show()
                startActivity(longIntent)
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signup.setOnClickListener {
            getResultText.launch(signUpIntent)
        }
    }
    private fun textNullChecker(): Boolean {
        if (binding.idEditText.text.length.toString() == "0" || binding.pwEditText.text.length.toString() == "0")
            return false
        return true
    }
}