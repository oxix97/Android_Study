package com.example.chapter5.view.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.chapter5.databinding.ActivitySpinnerBinding

class SpinnerActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpinnerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var data = listOf("1", "2", "3", "4", "5")
        var adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data)
        binding.spSpinner.adapter = adapter

        binding.spSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.tvResult.text = data.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }
}