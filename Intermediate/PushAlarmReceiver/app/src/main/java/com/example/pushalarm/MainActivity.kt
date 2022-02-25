package com.example.pushalarm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.pushalarm.base.baseutil.BaseViewUtil
import com.example.pushalarm.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initFirebase()
        updateResult()
    }

    private fun initFirebase() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful) {
                binding.tvMainToken.text = it.result
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateResult(isNewIntent: Boolean = false) {
        binding.tvMainResult.text =
            (intent.getStringExtra("notificationType") ?: "앱 런처") +
                    if (isNewIntent) {
                        "(으)로 갱신헀습니다."
                    } else {
                        "(으)로 실행했습니다."
                    }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        updateResult(true)
    }
}