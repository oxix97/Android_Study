package com.example.pushalarm

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
    }

    private fun initFirebase() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful) {
                binding.tvMainToken.setText("${it.result}")
                Log.d("token",it.result.toString())
            }
        }
    }
}