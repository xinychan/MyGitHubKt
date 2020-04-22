package com.example.xinychan.mygithubkt.utils

import android.support.design.widget.NavigationView
import android.support.v4.view.ViewCompat
import android.view.View
import com.example.xinychan.common.ext.otherwise
import com.example.xinychan.common.ext.yes

/**
 * NavigationView 扩展方法，判断导航界面展示和变化的逻辑
 */
inline fun NavigationView.doOnLayoutAvailable(crossinline block: () -> Unit) {
    ViewCompat.isLaidOut(this).yes {
        block()
    }.otherwise {
        addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                removeOnLayoutChangeListener(this)
                block()
            }
        })
    }

}