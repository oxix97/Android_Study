package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        this.binding = binding
        setContentView(binding.root)
        initKakaoLogin()
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    private fun initKakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                //Login Fail
            } else if (token != null) {
                //Login Success
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
        loginButtonClickListener(callback)
    }

    private fun loginButtonClickListener(callback: (OAuthToken?, Throwable?) -> Unit) {
        binding.ibLoginKakaoLogin.setOnClickListener {
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            UserApiClient.instance.run {
                if (isKakaoTalkLoginAvailable(this@LoginActivity)) {
                    loginWithKakaoTalk(this@LoginActivity, callback = callback)
                } else {
                    loginWithKakaoAccount(this@LoginActivity, callback = callback)
                }
            }
        }
    }
}