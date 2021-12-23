package com.example.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter5.adapter.CustomAdapter
import com.example.chapter5.data.RecyclerViewData
import com.example.chapter5.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data: MutableList<RecyclerViewData> = loadData()
        val adapter = CustomAdapter()
        adapter.listData = data
        binding.rvContainer.adapter = adapter
        binding.rvContainer.layoutManager = LinearLayoutManager(this)

    }

    private fun loadData(): MutableList<RecyclerViewData> {
        val data: MutableList<RecyclerViewData> = mutableListOf()
        for (no in 1..100) {
            val title = "이것이 안드로이드 $no"
            val date = System.currentTimeMillis()
            val memo = RecyclerViewData(no, title, date)
            data.add(memo)
        }
        return data
    }

}