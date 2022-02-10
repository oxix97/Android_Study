package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.base.BaseViewUtil
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var recyclerAdapter: RecyclerAdapter
    var helper: RoomHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initAdapter()
    }

    private fun initAdapter() {
        recyclerAdapter = RecyclerAdapter()
        helper =
            Room.databaseBuilder(this, RoomHelper::class.java, "room_memo")
                .allowMainThreadQueries()
                .build()
        recyclerAdapter.helper = helper
        recyclerAdapter.dataList.addAll(helper?.roomMemoDao()?.getAll() ?: listOf())

        with(binding) {
            rvMainContainer.adapter = recyclerAdapter
            rvMainContainer.layoutManager = LinearLayoutManager(this@MainActivity)
        }
        addMemo()
    }

    private fun addMemo() {
        with(binding) {
            btnMainSave.setOnClickListener {
                if (etMainMemo.text.isNotEmpty()) {
                    val memo =
                        RoomMemo(etMainMemo.text.toString(), System.currentTimeMillis())
                    helper?.roomMemoDao()?.insert(memo)
                    Log.d("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ", "$recyclerAdapter.dataList")
                    recyclerAdapter.dataList.clear()
                    etMainMemo.text.clear()
                    recyclerAdapter.notifyDataSetChanged()
                    recyclerAdapter.dataList.addAll(helper?.roomMemoDao()?.getAll() ?: listOf())
                }
            }
        }
    }
}