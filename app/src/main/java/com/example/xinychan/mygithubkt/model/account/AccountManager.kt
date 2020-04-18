package com.example.xinychan.mygithubkt.model.account

import com.example.xinychan.mygithubkt.utils.pref

/**
 * 账户信息
 */
object AccountManager {
    var username: String by pref("")
    var passwd: String by pref("")
    var token: String by pref("")

    fun isLoggedIn(): Boolean = TODO()
}