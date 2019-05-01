package com.stu.sample.user.PJC.study_glide

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide


fun loadInfo(context: Context,
             imageView: ImageView,
             url:String,
             placeholder: Int) {
    Glide.with(context)
            .load(url)
            .centerCrop()
            .placeholder(placeholder)
            .into(imageView)
}