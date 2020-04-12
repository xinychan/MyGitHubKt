package com.example.xinychan.mvp.impl

import android.support.v4.app.Fragment
import com.example.xinychan.mvp.IMvpView
import com.example.xinychan.mvp.IPresenter
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

abstract class BaseFragment<out P : BasePresenter<IMvpView<P>>> : IMvpView<P>, Fragment() {
    override val presenter: P

    init {
        presenter = createPresenterKt()
        presenter.view = this
    }

    fun createPresenterKt(): P {
        kotlin.coroutines.experimental.buildSequence {
            // 先获取当前 BaseFragment 类（包括子类）
            var thisClass: KClass<*> = this@BaseFragment::class
            while (true) {
                // 遍历当前类的父类
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap {
            // 获取当前类的泛型实参，作为 sequence 使用
            it.flatMap { it.arguments }.asSequence()
        }.first {
            // 获取第一个泛型实参，并判断是否是 IPresenter 子类
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            // 是 IPresenter 子类，则实例化子类作为参数 P
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }
    }
}

// 测试类
class MainFragment : BaseFragment<MainPresenter>()
class MainPresenter : BasePresenter<MainFragment>()