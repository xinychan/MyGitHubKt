package com.example.xinychan.mygithubkt.view.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.support.annotation.StringRes
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * 错误信息view
 */
class ErrorInfoView(val parentView: ViewGroup) : _RelativeLayout(parentView.context) {

    private var textView: TextView

    var isShowing = false

    init {
        this.setBackgroundColor(Color.WHITE)
        textView = TextView(parentView.context).apply {
            textSize = 18f
            this.setTextColor(Color.BLACK)
            this.setPadding(10, 10, 10, 10)
        }.lparams {
            this.addRule(RelativeLayout.CENTER_IN_PARENT)
        }
    }

    fun show(text: String) {
        if (!isShowing) {
            val layoutParams: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            parentView.addView(this, layoutParams)
            alpha = 0f
            animate().alpha(1f).setDuration(100).start()
            isShowing = true
        }
        textView.text = text
    }

    fun dismiss() {
        if (isShowing) {
            parentView.startViewTransition(this)
            parentView.removeView(this)
            animate().alpha(0f).setDuration(100).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    parentView.endViewTransition(this@ErrorInfoView)
                }
            }).start()
            isShowing = false
        }
    }

    fun show(@StringRes textRes: Int) {
        show(context.getString(textRes))
    }
}