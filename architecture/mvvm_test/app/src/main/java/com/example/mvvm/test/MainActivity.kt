package com.example.mvvm.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TestViewModel
    private lateinit var adapter: TestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TestAdapter({
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("name", it.name)
            intent.putExtra("number", it.number)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }, {
            deleteDialog(it)
        })
        Log.d("---","adapter end")

        val lm = LinearLayoutManager(this)
        with(binding) {
            rvMainContainer.adapter = adapter
            rvMainContainer.layoutManager = lm
            rvMainContainer.setHasFixedSize(true)
        }
        Log.d("---","adapter end")

//        viewModel = ViewModelProvider(this)[TestViewModel::class.java]
//        viewModel.getAll().observe(this) {
//            adapter.setTestData(it!! as MutableList<TestData>)
//        }

        binding.btnMainAddItem.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        initView()
    }

    private fun deleteDialog(data: TestData) {
        val builder = AlertDialog.Builder(this)
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                viewModel.delete(data)
            }
        builder.show()
    }

    private fun initView() {

    }


}