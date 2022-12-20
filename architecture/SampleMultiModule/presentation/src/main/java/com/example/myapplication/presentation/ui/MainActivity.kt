package com.example.myapplication.presentation.ui

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.presentation.base.BaseViewUtil
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {

    }
}