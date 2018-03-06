package com.liuhe.vlayoutsample.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.liuhe.vlayoutsample.App
import com.liuhe.vlayoutsample.R


/**
 * @author liuhe
 * @date 2018/3/6
 */
var defaultOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_neterror)
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

/**
 * 加载图片
 */
fun ImageView.loadImage(url: String) {
    Glide.with(App.sApp).applyDefaultRequestOptions(defaultOptions).load(url).into(this)
}

/**
 * 取消加载图片
 */
fun ImageView.clearImage() {
    Glide.with(App.sApp).clear(this)
}

