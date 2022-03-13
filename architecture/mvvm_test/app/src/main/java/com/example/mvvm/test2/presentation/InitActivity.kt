package com.example.mvvm.test2.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityInitBinding
import com.example.mvvm.test2.model.Entity
import com.example.mvvm.test2.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class InitActivity : AppCompatActivity() {
    private lateinit var  mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityInitBinding>(this, R.layout.activity_init)

        with(binding) {
            mainViewModel = ViewModelProvider(this@InitActivity)[MainViewModel::class.java]
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
                    lifecycleScope.launch {
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