package com.example.xinychan.mygithubkt.settings

import com.example.xinychan.common.log.logger
import com.example.xinychan.mygithubkt.AppContext
import com.example.xinychan.mygithubkt.utils.deviceId

/**
 * GitHub 上创建的app与账号相关信息
 */
object Configs {

    object Account {
        val SCOPES = listOf("user", "repo", "notifications", "gist", "admin:org")
        const val clientId = "1cb79b0638991dd6669a"
        const val clientSecret = "372fc0ad993c1c7a401614ab8387173b2c6e8630"
        const val note = "kotliner.cn"
        const val noteUrl = "http://www.kotliner.cn"

        val fingerPrint by lazy {
            (AppContext.deviceId + clientId).also { logger.info("fingerPrint: " + it) }
        }
    }

}