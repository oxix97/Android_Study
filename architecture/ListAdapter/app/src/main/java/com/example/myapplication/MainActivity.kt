package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainListAdapter: MainListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainListAdapter = MainListAdapter()
        mainListAdapter.submitList(STUB_DATA)
        binding.rvMainContainer.adapter = mainListAdapter

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