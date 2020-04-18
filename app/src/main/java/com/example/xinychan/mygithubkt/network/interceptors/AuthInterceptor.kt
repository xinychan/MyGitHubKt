package com.example.xinychan.mygithubkt.network.interceptors

import android.util.Base64
import com.example.xinychan.mygithubkt.model.account.AccountManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 登录鉴权拦截器
 */
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        return chain.proceed(original.newBuilder()
            .apply {
                when {
                    // 如果是鉴权接口（尚未登录），则添加登录信息（使用Base64编码）到请求头
                    original.url().pathSegments().contains("authorizations") -> {
                        val userCredentials = AccountManager.username + ":" + AccountManager.passwd
                        val auth = "Basic " + String(
                            Base64.encode(
                                userCredentials.toByteArray(),
                                Base64.DEFAULT
                            )
                        ).trim()
                        header("Authorization", auth)
                    }
                    // 如果已经登录，使用 Token 添加到请求头
                    AccountManager.isLoggedIn() -> {
                        val auth = "Token " + AccountManager.token
                        header("Authorization", auth)
                    }
                    else -> removeHeader("Authorization")
                }
            }
            .build())
    }

}