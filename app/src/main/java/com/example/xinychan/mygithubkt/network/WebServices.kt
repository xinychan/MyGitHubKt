package com.example.xinychan.mygithubkt.network

import com.example.xinychan.common.ext.ensureDir
import com.example.xinychan.mygithubkt.AppContext
import com.example.xinychan.mygithubkt.network.interceptors.AcceptInterceptor
import com.example.xinychan.mygithubkt.network.interceptors.AuthInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.github.com"

private val cacheFile: File by lazy {
    File(AppContext.cacheDir, "webServiceApi").apply { ensureDir() }
}

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(
            OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(Cache(cacheFile, 1024 * 1024 * 1024))
                .addInterceptor(AcceptInterceptor())
                .addInterceptor(AuthInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        ).baseUrl(BASE_URL).build()
}