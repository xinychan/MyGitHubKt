package com.example.xinychan.mygithubkt.network.entities

import com.example.xinychan.common.anno.Poko
import com.example.xinychan.mygithubkt.settings.Configs

/**
 * 鉴权网络请求返回的结果数据类
 */
@Poko
data class AuthorizationReq(
    var scopes: List<String> = Configs.Account.SCOPES,
    var note: String = Configs.Account.note,
    var note_url: String = Configs.Account.noteUrl,
    var client_secret: String = Configs.Account.clientSecret
)

@Poko
data class AuthorizationRsp(
    var id: Int,
    var url: String,
    var app: App,
    var token: String,
    var hashed_token: String,
    var token_last_eight: String,
    var note: String,
    var note_url: String,
    var created_at: String,
    var updated_at: String,
    var scopes: List<String>
)

@Poko
data class App(var name: String, var url: String, var client_id: String)