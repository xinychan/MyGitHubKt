package com.example.xinychan.mygithubkt.view

import android.os.Bundle
import com.example.xinychan.mvp.impl.BaseActivity
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.presenter.LoginPresenter

class LoginActivity : BaseActivity<LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
