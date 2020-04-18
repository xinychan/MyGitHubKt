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
        const val clientId = "cccb7d70ba4fe6d4f62d"
        const val clientSecret = "30bea5fc021ed503bef21e23ce8e2b02d588ab6c"
        const val note = "kotliner.cn"
        const val noteUrl = "http://www.kotliner.cn"

        val fingerPrint by lazy {
            (AppContext.deviceId + clientId).also { logger.info("fingerPrint: " + it) }
        }
    }

}