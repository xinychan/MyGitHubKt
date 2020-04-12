package com.example.xinychan.mvp.impl

import android.content.res.Configuration
import android.os.Bundle
import com.example.xinychan.mvp.IMvpView
import com.example.xinychan.mvp.IPresenter

abstract class BasePresenter<out V : IMvpView<BasePresenter<V>>> : IPresenter<V> {

    override lateinit var view: @UnsafeVariance V

    // 生命周期方法空实现，子类需要实现可以自己重写

    override fun onCreate(savedInstanceState: Bundle?) {}
    override fun onSaveInstanceState(outState: Bundle) {}
    override fun onViewStateRestored(savedInstanceState: Bundle?) {}
    override fun onConfigurationChanged(newConfig: Configuration) {}
    override fun onDestroy() {}
    override fun onStart() {}
    override fun onStop() {}
    override fun onResume() {}
    override fun onPause() {}
}