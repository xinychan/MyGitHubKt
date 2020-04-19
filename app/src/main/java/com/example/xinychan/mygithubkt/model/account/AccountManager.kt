package com.example.xinychan.mygithubkt.model.account

import com.example.xinychan.mygithubkt.network.entities.AuthorizationReq
import com.example.xinychan.mygithubkt.network.entities.AuthorizationRsp
import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.network.services.AuthServices
import com.example.xinychan.mygithubkt.network.services.UserServices
import com.example.xinychan.mygithubkt.utils.fromJson
import com.example.xinychan.mygithubkt.utils.pref
import com.google.gson.Gson
import retrofit2.HttpException
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 用户登录状态变化监听器
 * 通过接口通知界面登录状态
 */
interface OnAccountStateChangeListener {
    /**
     * 登录
     */
    fun onLogin(user: User)

    /**
     * 退出登录
     */
    fun onLogout()
}

/**
 * 账号管理模块
 * 管理登录/退出登录等请求
 */
object AccountManager {
    var authId: Int by pref(-1)
    var username: String by pref("")
    var passwd: String by pref("")
    var token: String by pref("")

    /**
     * 当前登录用户返回的Json信息
     */
    private var userJson: String by pref("")

    /**
     * 当前用户
     */
    var currentUser: User? = null
        get() {
            if (field == null && userJson.isNotEmpty()) {
                field = Gson().fromJson<User>(userJson)
            }
            return field
        }
        set(value) {
            if (value == null) {
                userJson = ""
            } else {
                userJson = Gson().toJson(value)
            }
            field = value
        }

    val onAccountStateChangeListeners = ArrayList<OnAccountStateChangeListener>()

    /**
     * 通知界面登录成功
     */
    private fun notifyLogin(user: User) {
        onAccountStateChangeListeners.forEach {
            it.onLogin(user)
        }
    }

    /**
     * 通知界面退出登录
     */
    private fun notifyLogout() {
        onAccountStateChangeListeners.forEach {
            it.onLogout()
        }
    }

    /**
     * 用户是否已经登录
     */
    fun isLoggedIn(): Boolean {
        return token.isNotEmpty()
    }

    /**
     * 用户登录
     */
    fun login() {
        AuthServices.createAuthorization(AuthorizationReq())
            // 请求完成后切换到主线程处理
            .observeOn(AndroidSchedulers.mainThread())
            // io 线程进行网络请求
            .subscribeOn(Schedulers.io())
            .doOnNext {
                // 已经登录过，返回 token 为空，需要删除 token 重新登录
                if (it.token.isEmpty()) {
                    throw AccountException(it)
                }
            }.retryWhen { observable ->
                observable.flatMap {
                    // 已经登录过，则删除 token
                    if (it is AccountException) {
                        AuthServices.deleteAuthorization(it.authorizationRsp.id)
                    } else {
                        Observable.error(it)
                    }
                }
            }.flatMap {
                // 获取登录后，获取用户信息
                token = it.token
                authId = it.id
                UserServices.getAuthenticatedUser()
            }.map {
                // 通知界面登录成功，更新界面
                currentUser = it
                notifyLogin(it)
            }
    }

    /**
     * 用户退出登录
     */
    fun logout() {
        // 退出登录，删除鉴权信息
        AuthServices.deleteAuthorization(authId)
            // 请求完成后切换到主线程处理
            .observeOn(AndroidSchedulers.mainThread())
            // io 线程进行网络请求
            .subscribeOn(Schedulers.io())
            .doOnNext {
                if (it.isSuccessful) {
                    // 退出登录成功，通知界面已经退出登录，更新界面
                    authId = -1
                    token = ""
                    currentUser = null
                    notifyLogout()
                } else {
                    throw HttpException(it)
                }
            }
    }

    /**
     * 登录失败的异常
     * 用于判断已经登录，token为空时的情况
     */
    class AccountException(val authorizationRsp: AuthorizationRsp) : Exception("Already logged in.")
}