package com.example.xinychan.common

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * SharePreference 扩展
 * @param context 上下文
 * @param name key-属性名称
 * @param default value-存储的值
 * @param prefName 属性默认名称
 */
class Preference<T>(
    val context: Context,
    val name: String,
    val default: T,
    val prefName: String = "default"
) : ReadWriteProperty<Any?, T> {

    // 创建 SharedPreferences
    private val perfs: SharedPreferences by lazy {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreferences(name)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreferences(name, value)
    }

    private fun findPreferences(key: String): T {
        return when (default) {
            is Long -> perfs.getLong(key, default)
            is Int -> perfs.getInt(key, default)
            is Boolean -> perfs.getBoolean(key, default)
            is String -> perfs.getString(key, default)
            else -> throw IllegalAccessException("Unsupported type.")
        } as T
    }


    private fun putPreferences(key: String, value: T) {
        val edit: SharedPreferences.Editor = perfs.edit()
        when (value) {
            is Long -> edit.putLong(key, value).apply()
            is Int -> edit.putInt(key, value).apply()
            is Boolean -> edit.putBoolean(key, value).apply()
            is String -> edit.putString(key, value).apply()
            else -> throw IllegalAccessException("Unsupported type.")
        }
    }

}