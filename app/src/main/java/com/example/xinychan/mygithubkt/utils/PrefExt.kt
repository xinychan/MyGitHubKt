package com.example.xinychan.mygithubkt.utils

import com.example.xinychan.common.sharedpreferences.Preference
import com.example.xinychan.mygithubkt.AppContext
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.pref(default: T): Preference<T> {
    return Preference(AppContext, "", default, R::class.jvmName)
}
