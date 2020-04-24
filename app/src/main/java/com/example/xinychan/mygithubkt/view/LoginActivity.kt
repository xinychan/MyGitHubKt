package com.example.xinychan.mygithubkt.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.xinychan.mvp.impl.BaseActivity
import com.example.xinychan.mygithubkt.MainActivity
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.presenter.LoginPresenter
import com.example.xinychan.mygithubkt.utils.hideSoftInput
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInButton.setOnClickListener {
            clickToLogin()
        }
    }

    // todo 登录报错：retrofit2.adapter.rxjava.HttpException: HTTP 401 Unauthorized
    // todo 可能账户密码已经修改，需要配置正确账户和密码，或者github鉴权规则修改，需要修改请求头
    // 点击进行登录
    private fun clickToLogin() {
        val name: String = username.text.toString()
        val psw: String = password.text.toString()
        hideSoftInput()
        presenter.doLogin(name, psw)
    }

    // 是否显示登录进度条
    private fun showProgress(show: Boolean) {
        val shortAnimTime: Int = resources.getInteger(android.R.integer.config_shortAnimTime)
        val loginFormAlpha: Float = (if (show) 0 else 1).toFloat()
        val loginFormVisible = if (show) View.GONE else View.VISIBLE
        loginForm.animate()
            .setDuration(shortAnimTime.toLong())
            .alpha(loginFormAlpha)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    loginForm.visibility = loginFormVisible
                }
            })

        val loginProgressAlpha: Float = (if (show) 1 else 0).toFloat()
        val loginProgressVisible = if (show) View.VISIBLE else View.GONE
        loginProgress.animate()
            .setDuration(shortAnimTime.toLong())
            .alpha(loginProgressAlpha)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    loginProgress.visibility = loginProgressVisible
                }
            })
    }

    private fun showTips(view: EditText, tips: String) {
        view.requestFocus()
        view.error = tips
    }

    fun onLoginStart() {
        showProgress(true)
    }

    fun onLoginError(e: Throwable) {
        e.printStackTrace()
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show()
        showProgress(false)
    }

    fun onLoginSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
        showProgress(false)
        startMainActivity()
    }

    fun onDataInit(name: String, passwd: String) {
        username.setText(name)
        password.setText(passwd)
    }

    private fun startMainActivity() {
        val intent: Intent = Intent()
        intent.setClass(this, MainActivity::class.java)
        this.startActivity(intent)
    }

}
