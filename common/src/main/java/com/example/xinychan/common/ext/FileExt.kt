package com.example.xinychan.common.ext

import android.util.Log
import java.io.File

private const val TAG = "FileExt"

/**
 * 创建文件夹
 * 不是文件夹则创建文件夹，是文件夹则返回 false
 */
fun File.ensureDir(): Boolean {
    try {
        isDirectory.no {
            isFile.yes {
                delete()
            }
            return mkdirs()
        }
    } catch (e: Exception) {
        Log.w(TAG, e.message)
    }
    return false
}