package com.example.mvvm.test2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityInitBinding
import com.example.mvvm.test2.viewmodel.MainViewModel
import com.example.mvvm.test2.viewmodel.RecyclerViewAdapter
import com.example.mvvm.test2.model.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InitActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityInitBinding>(this, R.layout.activity_init)
        //여기서 오류남
        binding.viewModel = viewModel

        val mAdapter = RecyclerViewAdapter(viewModel)
        binding.recyclerview.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.allUsers.observe(this) { users ->
            users?.let { mAdapter.setUsers(it) }
        }

        binding.button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.insert(
                    Entity(0, binding.edit.text.toString())
                )
            }
        }
    }
}