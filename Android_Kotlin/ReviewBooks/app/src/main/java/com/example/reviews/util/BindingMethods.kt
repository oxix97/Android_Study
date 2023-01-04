package com.example.reviews.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingMethods {
    @JvmStatic
    @BindingAdapter("image_load")
    fun loadImage(imageView: ImageView, url: String) {
        Glide
            .with(imageView.context)
            .load(url)
            .override(100, 100)
            .into(imageView)
    }
}
