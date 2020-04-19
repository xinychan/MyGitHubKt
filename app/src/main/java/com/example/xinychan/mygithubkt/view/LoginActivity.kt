package com.example.xinychan.mygithubkt.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.xinychan.common.ext.otherwise
import com.example.xinychan.common.ext.yes
import com.example.xinychan.mvp.impl.BaseActivity
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.presenter.LoginPresenter
import com.example.xinychan.mygithubkt.utils.hideSoftInput
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity<LoginPresenter>() {

    // 登录进度条
    private val loginProgress: ProgressBar by lazy {
        findViewById<ProgressBar>(R.id.loginProgress)
    }

    // 登录界面布局
    private val loginForm: LinearLayout by lazy {
        findViewById<LinearLayout>(R.id.loginForm)
    }

    // 用户名输入框
    private val username: AutoCompleteTextView by lazy {
        findViewById<AutoCompleteTextView>(R.id.username)
    }

    // 密码输入框
    private val password: EditText by lazy {
        findViewById<EditText>(R.id.password)
    }

    // 登录按钮
    private val signInButton: Button by lazy {
        findViewById<Button>(R.id.signInButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInButton.setOnClickListener {
            clickToLogin()
        }
    }

    // 点击进行登录
    private fun clickToLogin() {
        val name: String = username.text.toString()
        val psw: String = password.text.toString()
        presenter.checkUserName(name).yes {
            presenter.checkPasswd(psw).yes {
                hideSoftInput()
                presenter.doLogin(name, psw)
            }.otherwise {
                showTips(password, "密码不合法")
            }
        }.otherwise {
            showTips(username, "用户名不合法")
        }
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
                    loginForm.visibility = loginProgressVisible
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
        toast("登录失败")
        showProgress(false)
    }

    fun onLoginSuccess() {
        toast("登录成功")
        showProgress(false)
    }

    fun onDataInit(name: String, passwd: String) {
        username.setText(name)
        password.setText(passwd)
    }

}
