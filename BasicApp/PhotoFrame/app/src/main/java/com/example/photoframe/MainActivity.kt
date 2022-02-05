package com.example.photoframe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photoframe.base.BaseViewUtil
import com.example.photoframe.databinding.ActivityMainBinding

class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {

    }
}