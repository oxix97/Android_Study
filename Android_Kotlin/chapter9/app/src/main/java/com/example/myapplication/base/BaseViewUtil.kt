package com.example.myapplication.base

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

sealed class BaseViewUtil {
    abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layout: Int) : Fragment() {
        private var _binding: T? = null
        protected val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = DataBindingUtil.inflate(inflater, layout, container, false)
            return binding.root
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        abstract fun initView()
    }

    abstract class BaseAppCompatActivity<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) :
        AppCompatActivity() {
        protected lateinit var binding: T
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, layoutRes)
        }

        abstract fun permissionGranted(requestCode: Int)
        abstract fun permissionDenied(requestCode: Int)

        fun requirePermission(permissions: Array<String>, requestCode: Int) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                permissionGranted(requestCode)
            } else {
                val isAllPermissionGranted = permissions.all {
                    checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
                }
                if (isAllPermissionGranted) {
                    permissionGranted(requestCode)
                } else {
                    ActivityCompat.requestPermissions(this, permissions, requestCode)
                }
            }
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                permissionGranted(requestCode)
            } else {
                permissionDenied(requestCode)
            }
        }

        abstract fun initView()
    }

    abstract fun initView()


}