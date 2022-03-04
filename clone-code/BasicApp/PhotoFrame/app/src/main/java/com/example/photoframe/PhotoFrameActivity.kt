package com.example.photoframe

import android.net.Uri
import android.os.Bundle
import com.example.photoframe.base.BaseViewUtil
import com.example.photoframe.databinding.ActivityPhotoFrameBinding
import java.util.*
import kotlin.concurrent.timer

class PhotoFrameActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityPhotoFrameBinding>(R.layout.activity_photo_frame) {
    private val photoList = mutableListOf<Uri>()
    private var currentPosition = 0
    private var timer: Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        getIntentData()
        startTimer()
    }

    private fun getIntentData() {
        val size = intent.getIntExtra("photoListSize", 0)
        for (i in 0..size) {
            intent.getStringExtra("photo$i")?.let {
                photoList.add(Uri.parse(it))
            }
        }
    }

    private fun startTimer() {
        //5초에 한번씩 돈다.
        timer = timer(period = 5 * 1000) {
            runOnUiThread {
                val current = currentPosition
                val next = if (photoList.size <= currentPosition + 1) 0 else currentPosition + 1

                with(binding) {
                    ivPhotoframeBackgroundImage.setImageURI(photoList[current])
                    ivPhotoframePhotoImage.alpha = 0f
                    ivPhotoframePhotoImage.setImageURI(photoList[next])
                    ivPhotoframePhotoImage.animate()
                        .alpha(1.0f)
                        .setDuration(1000)
                        .start()
                    currentPosition = next
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }

    override fun onStart() {
        super.onStart()
        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}