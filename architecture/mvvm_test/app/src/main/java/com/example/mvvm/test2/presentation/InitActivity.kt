package com.example.mvvm.test2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityInitBinding
import com.example.mvvm.test2.viewmodel.MainViewModel
import com.example.mvvm.test2.model.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InitActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityInitBinding>(this, R.layout.activity_init)

        with(binding) {
            viewModel = mainViewModel

            val mAdapter = RecyclerViewAdapter(mainViewModel)

            recyclerview.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(applicationContext)
            }

            mainViewModel.allUsers.observe(this@InitActivity) { users ->
                users?.let { mAdapter.setUsers(it) }
            }

            button.setOnClickListener {
                if (edit.text.isNotEmpty()) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        mainViewModel.insert(
                            Entity(0, edit.text.toString())
                        )
                        edit.text.clear()
                    }
                } else {
                    Toast.makeText(this@InitActivity, "input text plz", Toast.LENGTH_SHORT).show()
                }
            }

            btnInitClear.setOnClickListener {
                lifecycleScope.launch {
                    mainViewModel.deleteAll()
                }
            }

        }
    }
}