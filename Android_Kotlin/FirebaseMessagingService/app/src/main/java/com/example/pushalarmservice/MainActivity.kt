package com.example.pushalarmservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

class MainActivity : AppCompatActivity() {


    private val textView: TextView by lazy {
        findViewById(R.id.tv_text)
    }

    private val title: TextView by lazy {
        findViewById(R.id.tv_title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFirebase()
        updateResult()
    }

    private fun updateResult(isChange: Boolean = false) {
        title.text = if (isChange) {
            "(으)로 갱신했습니다."
        } else {
            "(으)로 실행했습니다."
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        updateResult(true)
    }
    private fun initFirebase() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                textView.text = task.result
                Log.d("TOKEN : ", task.result)
            }
        }
    }
}