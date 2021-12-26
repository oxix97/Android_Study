package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isOperator = false
    private var hasOperator = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun buttonClicked(v: View) {
        when (v.id) {
            R.id.btn_0 -> numberButtonClicked("0")
            R.id.btn_1 -> numberButtonClicked("1")
            R.id.btn_2 -> numberButtonClicked("2")
            R.id.btn_3 -> numberButtonClicked("3")
            R.id.btn_4 -> numberButtonClicked("4")
            R.id.btn_5 -> numberButtonClicked("5")
            R.id.btn_6 -> numberButtonClicked("6")
            R.id.btn_7 -> numberButtonClicked("7")
            R.id.btn_8 -> numberButtonClicked("8")
            R.id.btn_9 -> numberButtonClicked("9")
            R.id.btn_plus -> operatorButtonClicked("+")
            R.id.btn_minus -> operatorButtonClicked("-")
            R.id.btn_multi -> operatorButtonClicked("*")
            R.id.btn_divide -> operatorButtonClicked("/")
            R.id.btn_modulo -> operatorButtonClicked("%")
        }
    }

    private fun numberButtonClicked(number: String) {
        if (isOperator) {
            binding.tvExpression.append(" ")
        }
        isOperator = false
        val expressionText = binding.tvExpression.text.split(" ")
        if (expressionText.isNotEmpty() && expressionText.last().length >= 15) {
            Toast.makeText(this, "15자리 까지만 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
            return
        } else if (number == "0" && expressionText.last().isEmpty()) {
            Toast.makeText(this, "0은 제일 앞에 올 수 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        binding.tvExpression.append(number)
    }

    private fun operatorButtonClicked(operator: String) {
        if (binding.tvExpression.text.isEmpty()) {
            return
        }
        when {
            isOperator -> {
                val text = binding.tvExpression.text.toString()
                binding.tvExpression.text = text.dropLast(1)
            }
            hasOperator -> {
                Toast.makeText(this, "연산자는 한 번만 사용 가능합니다.", Toast.LENGTH_SHORT).show()
                return
            }
            else -> {
                binding.tvExpression.append(" $operator")
            }
        }
        val ssb = SpannableStringBuilder(binding.tvExpression.text)
        ssb.setSpan(
            ForegroundColorSpan(
                getColor(R.color.green)
            ),
            binding.tvExpression.text.length - 1,
            binding.tvExpression.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvExpression.text = ssb
        isOperator = true
        hasOperator = true
    }

    fun clearButtonClicked(v: View) {

    }

    fun resultButtonClicked(v: View) {

    }

    private fun historyButtonClicked(v: View) {

    }

}