package com.example.xinychan.mygithubkt.utils

fun Int.toKilo(): String {
    return if (this > 500) {
        "${(Math.round(this / 100f) / 10f)}k"
    } else {
        "$this"
    }
}