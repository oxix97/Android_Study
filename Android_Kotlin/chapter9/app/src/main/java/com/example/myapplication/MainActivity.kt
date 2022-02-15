package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.base.BaseViewUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.util.shortToast

class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var registerLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initGallery()
        cameraResult()
    }

    private fun setViews() {
        binding.btnMainCamera.setOnClickListener {
            requirePermission(
                arrayOf(Manifest.permission.CAMERA),
                PERM_CAMERA
            )
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra("REQ_CAMERA", REQ_CAMERA)
        registerLauncher.launch(intent)
    }

    private fun cameraResult() {
        registerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val requestCode = intent.getIntExtra("REQ_CAMERA", 0)
                    when (requestCode) {
                        REQ_CAMERA -> {
                            val data = intent?.extras?.get("data")
                            if (data != null) {
                                val bitmap = data as Bitmap
                                binding.ivMainImage.setImageBitmap(bitmap)
                            }
                        }
                    }
                }
            }

    }


    //저장소 승인 요청 완료
    override fun permissionGranted(requestCode: Int) {
        when (requestCode) {
            PERM_STORAGE -> setViews()
            PERM_CAMERA -> openCamera()
        }
    }

    //저장소 승인 요청 거부시 앱종료
    override fun permissionDenied(requestCode: Int) {
        when (requestCode) {
            PERM_STORAGE -> {
                shortToast("외부 저장소 권한을 승인해야 앱을 사용할 수 있습니다.")
                finish()
            }
            PERM_CAMERA -> {
                shortToast("권한을 승인해야 카메라를 사용할 수 있다.")
            }
        }
    }

    private fun initGallery() {
        requirePermission(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            PERM_STORAGE
        )
    }

    companion object {
        const val PERM_STORAGE = 99
        const val PERM_CAMERA = 100
        const val REQ_CAMERA = 101
    }
}