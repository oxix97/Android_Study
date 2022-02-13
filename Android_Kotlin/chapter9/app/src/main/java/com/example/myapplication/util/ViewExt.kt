package com.example.myapplication.util

import android.content.Context
import android.widget.Toast

fun Context.shortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}