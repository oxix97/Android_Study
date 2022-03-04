package com.example.lottoselector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private val clearButton: Button by lazy {
        //초기화 버튼
        findViewById(R.id.reset)
    }

    private val addButton: Button by lazy {
        // 숫자 선택 버튼
        findViewById(R.id.addButton)
    }

    private val runButton: Button by lazy {
        //버튼 누르면 숫자 랜덤생성
        findViewById(R.id.runButton)
    }

    private val numberPicker: NumberPicker by lazy {
        //1~45 까지 있는 numberPicker
        findViewById(R.id.numberPicker)
    }

    private val pickNumberSet = hashSetOf<Int>() // numberPicker 초기화
    private var didRun = false // 초기 false 화면에 아무것도 안보임

    private val numberTextViewList: List<TextView> by lazy {
        //textView를 List로 변경하고 가져오기
        listOf<TextView>(
            findViewById(R.id.textView1),
            findViewById(R.id.textView2),
            findViewById(R.id.textView3),
            findViewById(R.id.textView4),
            findViewById(R.id.textView5),
            findViewById(R.id.textView6)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
        initClearButton()
    }

    private fun initClearButton() {
        clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTextViewList.forEach {
                it.isVisible = false
            }
            didRun = false
        }
    }

    private fun initAddButton() {
        addButton.setOnClickListener {
            if (didRun) {
                Toast.makeText(this, "초기화 후에 시도해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pickNumberSet.size >= 5) {
                Toast.makeText(this, "번호 개수는 5개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pickNumberSet.contains(numberPicker.value)) {
                Toast.makeText(this, "이미 선택한 번호 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            textView.text = numberPicker.value.toString()

            setNumberBackground(numberPicker.value, textView)

            pickNumberSet.add(numberPicker.value)

        }
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
                if (pickNumberSet.contains(i)) {
                    continue
                }
                this.add(i)
            }
        }
        numberList.shuffle()

        val newList = pickNumberSet.toList() + numberList.subList(0, 6 - pickNumberSet.size)
        return newList.sorted()
    }

}




