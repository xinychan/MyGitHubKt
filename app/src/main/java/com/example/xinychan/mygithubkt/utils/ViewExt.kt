package com.example.xinychan.mygithubkt.utils

import android.widget.TextView
import com.zzhoujay.richtext.RichText

/**
 * TextView 扩展方法
 */
var TextView.markdownText: String
    get() {
        return text.toString()
    }
    set(value) {
        RichText.fromMarkdown(value).into(this)
    }