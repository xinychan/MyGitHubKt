package com.example.xinychan.mygithubkt.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * 隐藏和展示输入键盘的扩展方法
 */
fun Context.toggleSoftInput() {
    val manager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun View.showSoftInput(): Boolean {
    val manager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return manager.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun Activity.showSoftInput(): Boolean {
    return this.currentFocus?.showSoftInput() ?: false
}

fun View.hideSoftInput(): Boolean {
    val manager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return manager.hideSoftInputFromWindow(windowToken, 0)
}

fun Activity.hideSoftInput(): Boolean {
    return this.currentFocus?.hideSoftInput() ?: false
}

fun Context.isActive(): Boolean {
    val manager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return manager.isActive
}
