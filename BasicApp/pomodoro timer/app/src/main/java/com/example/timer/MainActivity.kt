package com.example.timer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import com.example.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentCountDownTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()

    }

    private fun bindViews() {
        binding.sbMainTimeCount.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    updateRemainTime(progress * 60 * 1200L)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                currentCountDownTimer?.cancel()
                currentCountDownTimer = null
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar ?: return
                currentCountDownTimer = createCountDownTimer(seekBar.progress * 60 * 1000L)
                currentCountDownTimer?.start()
            }
        })
    }

    private fun createCountDownTimer(initialMillis: Long) =
        object : CountDownTimer(initialMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                updateRemainTime(millisUntilFinished)
                updateSeekbar(millisUntilFinished)
            }

            override fun onFinish() {
                updateRemainTime(0)
                updateSeekbar(0)
            }
        }

    @SuppressLint("SetTextI18n")
    private fun updateRemainTime(remainMillis: Long) {
        val remainSeconds = remainMillis / 1000L
        with(binding) {
            tvMainMinute.text = "%02d".format(remainSeconds / 60)
            tvMainSecond.text = "%02d".format(remainSeconds % 60)
        }
    }

    private fun updateSeekbar(remainMillis: Long) {
        binding.sbMainTimeCount.progress = (remainMillis / 60 / 1200L).toInt()
    }

}