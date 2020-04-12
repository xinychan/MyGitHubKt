package com.example.xinychan.mvp

/**
 * mvp 框架基础接口
 * Presenter 与 View 互相持有，以泛型的方式传入
 */
interface IPresenter<out View : IMvpView<IPresenter<View>>> : ILifecycle {
    val view: View
}

interface IMvpView<out Presenter : IPresenter<IMvpView<Presenter>>> : ILifecycle {
    val presenter: Presenter
}