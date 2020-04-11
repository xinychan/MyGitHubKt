package com.example.xinychan.common

/**
 * Boolean 扩展方法
 */
sealed class BooleanExt<out T>

object Otherwise : BooleanExt<Nothing>()
class WithData<T>(val data: T) : BooleanExt<T>()

inline fun <T> Boolean.yes(block: () -> T): BooleanExt<T> {
    return when {
        this -> {
            WithData(block())
        }
        else -> {
            Otherwise
        }
    }
}

inline fun <T> Boolean.no(block: () -> T): BooleanExt<T> {
    return when {
        this -> {
            Otherwise
        }
        else -> {
            WithData(block())
        }
    }
}

inline fun <T> BooleanExt<T>.otherwise(block: () -> T): T {
    return when (this) {
        is Otherwise -> block()
        is WithData -> this.data
    }
}