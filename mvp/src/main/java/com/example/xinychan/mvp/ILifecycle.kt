package com.example.xinychan.mvp

import android.content.res.Configuration
import android.os.Bundle

/**
 * 对应 activity/fragment 生命周期的接口
 */
interface ILifecycle {

    fun onCreate(savedInstanceState: Bundle?)

    fun onSaveInstanceState(outState: Bundle)

    fun onViewStateRestored(savedInstanceState: Bundle?)

    fun onConfigurationChanged(newConfig: Configuration)

    fun onDestroy()

    fun onStart()

    fun onStop()

    fun onResume()

    fun onPause()

}