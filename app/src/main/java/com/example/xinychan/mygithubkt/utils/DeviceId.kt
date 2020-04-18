package com.example.xinychan.mygithubkt.utils

import android.content.Context
import android.provider.Settings

/**
 * 简易的获取设备id
 */
val Context.deviceId: String
    get() {
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }