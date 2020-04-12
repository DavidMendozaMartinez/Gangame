package com.davidmendozamartinez.gangame.commons

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {
    GlideApp.with(imageView)
        .load(url)
        .into(imageView)
}