package com.example.mykotlinapp.util.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mykotlinapp.R

fun ImageView.loadImage(url: String)
{
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_chonhinhanh)
        .error(R.drawable.ic_chonhinhanh)
        .into(this)
}