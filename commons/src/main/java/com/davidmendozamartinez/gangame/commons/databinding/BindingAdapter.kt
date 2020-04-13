package com.davidmendozamartinez.gangame.commons.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.davidmendozamartinez.gangame.commons.GlideApp

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {
    GlideApp.with(imageView)
        .load(url)
        .into(imageView)
}