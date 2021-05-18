package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val clearButton: Button by lazy {
        findViewById(R.id.resetButton)
    }

    private val runButton: Button by lazy {
        findViewById(R.id.runButton)
    }
    private val pickNumberSet1 = hashSetOf<Int>()
    private val pickNumberSet2 = hashSetOf<Int>()
    private val pickNumberSet3 = hashSetOf<Int>()
    private val pickNumberSet4 = hashSetOf<Int>()
    private val pickNumberSet5 = hashSetOf<Int>()

    private var didRun = false

    private val numberTextViewList: List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.textView11),
            findViewById(R.id.textView12),
            findViewById(R.id.textView13),
            findViewById(R.id.textView14),
            findViewById(R.id.textView15),
            findViewById(R.id.textView16),

            findViewById(R.id.textView21),
            findViewById(R.id.textView22),
            findViewById(R.id.textView23),
            findViewById(R.id.textView24),
            findViewById(R.id.textView25),
            findViewById(R.id.textView26),

            findViewById(R.id.textView31),
            findViewById(R.id.textView32),
            findViewById(R.id.textView33),
            findViewById(R.id.textView34),
            findViewById(R.id.textView35),
            findViewById(R.id.textView36),

            findViewById(R.id.textView41),
            findViewById(R.id.textView42),
            findViewById(R.id.textView43),
            findViewById(R.id.textView44),
            findViewById(R.id.textView45),
            findViewById(R.id.textView46),

            findViewById(R.id.textView51),
            findViewById(R.id.textView52),
            findViewById(R.id.textView53),
            findViewById(R.id.textView54),
            findViewById(R.id.textView55),
            findViewById(R.id.textView56),
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRunButton()
        initClearButton()

    }

    private fun setNumberBackground(number: Int, textView: TextView) {
        when (number) {
            in 1..9 ->
                textView.background = ContextCompat.getDrawable(this, R.drawable.yellow)
            in 10..19 ->
                textView.background = ContextCompat.getDrawable(this, R.drawable.blue)
            in 20..29 ->
                textView.background = ContextCompat.getDrawable(this, R.drawable.red)
            in 30..39 ->
                textView.background = ContextCompat.getDrawable(this, R.drawable.gray)

            else -> textView.background = ContextCompat.getDrawable(this, R.drawable.green)
        }
    }

    private fun initRunButton() {
        runButton.setOnClickListener {
            val list = getRandomNumber()
            didRun = true
            list.forEachIndexed { index, number ->
                val textView = numberTextViewList[index]
                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number, textView)
            }
        }
    }

    private fun getRandomNumber(): List<Int> {
        val numberList = mutableListOf<Int>().apply {
            for (i in 1..45) {
                if (pickNumberSet1.contains(i)) {
                    continue
                }
                this.add(i)
            }
        }
        numberList.shuffle()

        val newList = pickNumberSet1.toList() + numberList.subList(0, 6 - pickNumberSet1.size)
        return newList.sorted()
    }


    private fun initClearButton() {
        clearButton.setOnClickListener {
            pickNumberSet1.clear()
            numberTextViewList.forEach {
                it.isVisible = false
            }
            didRun = false
        }
    }
}