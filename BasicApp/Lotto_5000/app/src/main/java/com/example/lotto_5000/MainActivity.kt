package com.example.lotto_5000

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private val pickNumberSet = hashSetOf<Int>()
    private var didRun = false
    private val button: Button by lazy {
        findViewById(R.id.button)
    }

    private val textViewList1: List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.textView11),
            findViewById(R.id.textView12),
            findViewById(R.id.textView13),
            findViewById(R.id.textView14),
            findViewById(R.id.textView15),
        )
    }

    private val textViewList2: List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.textView21),
            findViewById(R.id.textView22),
            findViewById(R.id.textView23),
            findViewById(R.id.textView24),
            findViewById(R.id.textView25),
        )
    }

    private val textViewList3: List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.textView31),
            findViewById(R.id.textView32),
            findViewById(R.id.textView33),
            findViewById(R.id.textView34),
            findViewById(R.id.textView35),
        )
    }

    private val textViewList4: List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.textView41),
            findViewById(R.id.textView42),
            findViewById(R.id.textView43),
            findViewById(R.id.textView44),
            findViewById(R.id.textView45),
        )
    }

    private val textViewList5: List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.textView51),
            findViewById(R.id.textView52),
            findViewById(R.id.textView53),
            findViewById(R.id.textView54),
            findViewById(R.id.textView55),
        )
    }

    private fun getRandomNumber(): List<Int> {
        val numberList = mutableListOf<Int>().apply {
            for (i in 1..45) {
                if (pickNumberSet.contains(i)) {
                    continue
                }
                this.add(i)
            }
        }
        numberList.shuffle()
        val newList = pickNumberSet.toList()
        return newList.sorted()
    }

    private fun buttonOnClick() {
        button.setOnClickListener {
            val list = getRandomNumber()
            didRun = true
            list.forEachIndexed{index,number->
                val textView1 = textViewList1[index]
                val textView2 = textViewList2[index]
                val textView3 = textViewList3[index]
                val textView4 = textViewList4[index]
                val textView5 = textViewList5[index]
                textView1.text=number.toString()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}