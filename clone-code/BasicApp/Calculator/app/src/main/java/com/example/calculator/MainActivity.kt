package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.room.Room
import com.example.calculator.data.AppDatabase
import com.example.calculator.data.History
import com.example.calculator.databinding.ActivityMainBinding
import org.w3c.dom.Text
import kotlin.math.exp

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isOperator = false
    private var hasOperator = false
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "historyDB"
        ).build()

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
        binding.tvResultView.text = calculateExpression()


    }

    private fun operatorButtonClicked(operator: String) {
        if (binding.tvExpression.text.isEmpty()) {
            return
        }
        when {
            isOperator -> {
                val text = binding.tvExpression.text.toString()
                binding.tvExpression.text = text.dropLast(1) + operator
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
        binding.tvExpression.text = ""
        clearText()
    }

    fun resultButtonClicked(v: View) {
        val expressionText = binding.tvExpression.text.split(" ")

        if (binding.tvExpression.text.isEmpty() || expressionText.size == 1) {
            return
        }
        if (expressionText.size != 3 && hasOperator) {
            Toast.makeText(this, "미완성 수식", Toast.LENGTH_SHORT).show()
            return
        }
        if (expressionText[0].isNumber().not() || expressionText[2].isNumber().not()) {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            return
        }
        val text = binding.tvExpression.text.toString()
        val result = calculateExpression()

        Thread(
            Runnable {
                db.historyDao().insertHistory(History(null, text, result))
                println(text)
                println(result)
            }
        ).start()

        binding.tvExpression.text = binding.tvResultView.text
        clearText()
    }

    private fun clearText() {
        binding.tvResultView.text = ""
        isOperator = false
        hasOperator = false
    }

    private fun calculateExpression(): String {
        val expressionText = binding.tvExpression.text.split(" ")

        if (hasOperator.not() || expressionText.size != 3) {
            return ""
        }
        if (expressionText[0].isNumber().not() || expressionText[2].isNumber().not()) {
            return ""
        }
        val exp1 = expressionText[0].toBigInteger()
        val exp2 = expressionText[2].toBigInteger()

        return when (expressionText[1]) {
            "+" -> (exp1 + exp2).toString()
            "-" -> (exp1 - exp2).toString()
            "*" -> (exp1 * exp2).toString()
            "/" -> (exp1 / exp2).toString()
            "%" -> (exp1 % exp2).toString()
            else -> ""
        }
    }

    fun historyCloseClicked(v: View) {
        binding.llHistoryLayout.isVisible = false
    }

    fun historyButtonClicked(v: View) {
        binding.llHistoryLayout.isVisible = true
        binding.llHistoryRow.removeAllViews()

        Thread(Runnable {
            db.historyDao().getAll().reversed().forEach {
                runOnUiThread {
                    val historyView =
                        LayoutInflater
                            .from(this)
                            .inflate(R.layout.history_row, null, false)
                    historyView.findViewById<TextView>(R.id.tv_expression).text = it.expression
                    historyView.findViewById<TextView>(R.id.tv_resultView).text = "= ${it.result}"
                    binding.llHistoryRow.addView(historyView)
                }
            }
        }).start()
    }

    fun historyClearClicked(v: View) {
        binding.llHistoryRow.removeAllViews()
        Thread(Runnable {
            db.historyDao().deleteAll()
        }).start()
    }

    private fun String.isNumber(): Boolean {
        return try {
            this.toBigInteger()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}

