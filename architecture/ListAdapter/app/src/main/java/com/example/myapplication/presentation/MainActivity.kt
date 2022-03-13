package com.example.myapplication.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.MainData
import com.example.myapplication.util.base.BaseViewUtil
import com.example.myapplication.util.shortToast
import com.example.myapplication.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.*

class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = MainViewModel(application)

        with(binding) {
            viewModel = mainViewModel
            val mAdapter = MainListAdapter(mainViewModel)

            rvMainContainer.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(applicationContext)
            }

            mainViewModel.dataList.observe(this@MainActivity) { list ->
                list?.let {
                    mAdapter.submitList(it)
                }
            }

            btnMainAdd.setOnClickListener {
                if (etMainText.text.isNotEmpty()) {
                    lifecycleScope.launch {
                        mainViewModel.insert(
                            MainData(
                                0,
                                "title",
                                etMainText.text.toString()
                            )
                        )
                        etMainText.text.clear()
                    }
                } else {
                    shortToast("input text plz")
                }
            }
        }
    }

    override fun initView() {

    }
}