package com.example.xinychan.mygithubkt.utils

import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * AvatarImageView 扩展方法
 */
fun AvatarImageView.loadWithGlide(
    url: String,
    textPlaceHolder: Char,
    requestOptions: RequestOptions = RequestOptions()
) {
    textPlaceHolder.toString().let {
        setTextAndColorSeed(it.toUpperCase(), it.hashCode().toString())
    }

    Glide.with(this.context).load(url).apply(requestOptions).into(this)
}