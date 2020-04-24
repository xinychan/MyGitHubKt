package com.example.xinychan.mygithubkt.presenter

import android.os.Handler
import android.text.TextUtils
import com.example.xinychan.mvp.impl.BasePresenter
import com.example.xinychan.mygithubkt.BuildConfig
import com.example.xinychan.mygithubkt.model.account.AccountManager
import com.example.xinychan.mygithubkt.view.LoginActivity

class LoginPresenter : BasePresenter<LoginActivity>() {

    /**
     * 点击登录
     */
    fun doLogin(name: String, passwd: String) {
        AccountManager.username = name
        AccountManager.passwd = passwd
        view.onLoginStart()
        // 登录成功展示成功界面，登录失败展示失败界面
        val checkLogin: () -> Unit = fun() {
            if (checkUserName(name) && checkPasswd(passwd)) {
                view.onLoginSuccess()
            } else {
                val loginException = Exception()
                view.onLoginError(loginException)
            }
        }
        Handler().postDelayed(checkLogin,2000L)
    }

    /**
     * 验证用户名
     */
    fun checkUserName(name: String): Boolean {
        return TextUtils.equals("123456",name)
    }

    /**
     * 验证密码
     */
    fun checkPasswd(passwd: String): Boolean {
        return TextUtils.equals("123456",passwd)
    }

    override fun onResume() {
        super.onResume()
        view.onDataInit(AccountManager.username, AccountManager.passwd)
        if(BuildConfig.DEBUG){
            view.onDataInit(BuildConfig.testUserName, BuildConfig.testPassword)
        } else {
            view.onDataInit(AccountManager.username, AccountManager.passwd)
        }
    }
}