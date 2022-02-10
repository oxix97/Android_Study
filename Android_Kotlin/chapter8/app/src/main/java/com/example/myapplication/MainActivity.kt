package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.base.BaseViewUtil
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var recyclerAdapter: RecyclerAdapter
    val helper = SqliteHelper(this, "memo", 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initAdapter()
    }

    private fun initAdapter() {
        recyclerAdapter = RecyclerAdapter()
        recyclerAdapter.helper = helper
        recyclerAdapter.dataList.addAll(helper.selectMemo())

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
                    val memo = Memo(null, etMainMemo.text.toString(), System.currentTimeMillis())
                    helper.insertMemo(memo)
                }
                Log.d("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ", "$recyclerAdapter.dataList")
                recyclerAdapter.dataList.addAll(helper.selectMemo())
                recyclerAdapter.dataList.clear()
                recyclerAdapter.notifyDataSetChanged()
                etMainMemo.text.clear()
            }
        }
    }
}