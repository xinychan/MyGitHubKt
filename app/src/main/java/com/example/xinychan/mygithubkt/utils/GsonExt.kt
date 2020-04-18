package com.example.xinychan.mygithubkt.utils

import com.google.gson.Gson

/**
 * Gson 工具类
 */
inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)