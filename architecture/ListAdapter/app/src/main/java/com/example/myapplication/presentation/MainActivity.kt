package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.MainData
import com.example.myapplication.viewmodel.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        with(binding) {
            viewModel = mainViewModel
            val mAdapter = MainListAdapter(mainViewModel)

            rvMainContainer.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(applicationContext)
            }

            mainViewModel.dataList.observe(this@MainActivity) { list ->
                list?.let {
                    mAdapter.setDataList(it)
                }
            }
        }

    }

    companion object {
        private val STUB_DATA = listOf(
            MainData(0, "title0", "Content", Date()),
            MainData(1, "title1", "Content1", Date()),
            MainData(2, "title2", "Content22", Date()),
            MainData(3, "title3", "Content333", Date()),
            MainData(4, "title4", "Content4444", Date()),
            MainData(5, "title5", "Content55555", Date()),
            MainData(6, "title6", "Content666666", Date()),
            MainData(7, "title7", "Content7777777", Date()),
        )
    }
}