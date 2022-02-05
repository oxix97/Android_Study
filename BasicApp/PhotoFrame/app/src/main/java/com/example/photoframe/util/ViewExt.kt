package com.example.photoframe.util

import android.content.Context
import android.widget.Toast


fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
