package com.example.mvvm.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var viewModel: TestViewModel
    private var id: Long? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this).get(TestViewModel::class.java)

        if (intent != null && intent.hasExtra("name") &&
            intent.hasExtra("number") && intent.hasExtra("id")
        ) {
            with(binding) {
                etAddName.setText(intent.getStringExtra("name"))
                etAddNumber.setText(intent.getStringExtra("number"))
                id = intent.getLongExtra("id", -1)
            }
        }
        with(binding) {
            btnAddDone.setOnClickListener {
                val name = etAddName.text.toString().trim()
                val number = etAddNumber.text.toString()

                if (name.isEmpty() || number.isEmpty()) {
                    Toast.makeText(this@AddActivity, "입력이 안되어있는 부분이 있음", Toast.LENGTH_SHORT).show()
                } else {
                    val initial = name[0].uppercase()
                    val data = TestData(id, name, number, initial)
                    viewModel.insert(data)
                    finish()
                }
            }
        }
    }
}