package com.example.chapter5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.chapter5.databinding.ActivityMain2Binding
import com.example.chapter5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        getResultText =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    Toast.makeText(
                        this,
                        it.data?.getStringExtra("ok"),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        initSubActivity()

        setContentView(binding.root)
    }

    private fun initSubActivity() {
        binding.btnInitSub.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("title", "SubActivity")
            intent.putExtra("value", "실행 완료")
            intent.putExtra("number", 222)
            getResultText.launch(intent)
        }
    }
}
