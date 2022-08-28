package com.example.countriesapp.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter


import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.countriesapp.R

// Extension

fun ImageView.downloadFromUrl(url: String?,progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeholderProgessBar(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadFromUrl(view: ImageView, url: String?) {
    view.downloadFromUrl(url, placeholderProgessBar(view.context))
}