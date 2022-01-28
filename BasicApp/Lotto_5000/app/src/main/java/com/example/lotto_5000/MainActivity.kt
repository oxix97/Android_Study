package com.example.lotto_5000

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.room.Room
import com.example.lotto_5000.data.NumberData
import com.example.lotto_5000.data.NumberDatabase
import com.example.lotto_5000.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NumberDatabase
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            NumberDatabase::class.java,
            "numberDB"
        ).build()
    }

    private fun insertButtonClicked() {
        binding.llNumberRow.removeAllViews()
        Thread {
            db.numberDao().getAll().forEach {
                runOnUiThread {
                    val numberListView = LayoutInflater
                        .from(this)
                        .inflate(R.layout.item_number, null, false)

                    numberListView.findViewById<TextView>(R.id.tv1).text = it.number1
                    numberListView.findViewById<TextView>(R.id.tv2).text = it.number2
                    numberListView.findViewById<TextView>(R.id.tv3).text = it.number3
                    numberListView.findViewById<TextView>(R.id.tv4).text = it.number4
                    numberListView.findViewById<TextView>(R.id.tv5).text = it.number5
                    numberListView.findViewById<TextView>(R.id.tv6).text = it.number6

                    binding.llNumberRow.addView(numberListView)
                }
            }
        }.start()
    }

    private fun clearButtonClicked() {
        binding.llNumberRow.removeAllViews()
        Thread {
            db.numberDao().deleteAll()
        }.start()
    }

    private fun setNumbers(): List<Int> {
        val set: HashSet<Int> = HashSet()
        while (set.size != 6) {
            val num = (Math.random() * 45.0 + 1).toInt()
            set.add(num)
        }
        val list: ArrayList<Int> = ArrayList(set)
        list.sort()

        return list
    }
}