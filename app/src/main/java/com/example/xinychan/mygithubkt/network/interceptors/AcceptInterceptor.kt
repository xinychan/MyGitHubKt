package com.example.xinychan.mygithubkt.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * GitHub 请求时需要添加的通用请求头
 */
class AcceptInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        return chain.proceed(original.newBuilder().apply {
            header(
                "accept",
                "application/vnd.github.v3.full+json, ${original.header("accept") ?: ""}"
            )
        }.build())
    }

}