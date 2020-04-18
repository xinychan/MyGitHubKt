package com.example.xinychan.mygithubkt.network.services

import com.example.xinychan.mygithubkt.network.entities.AuthorizationReq
import com.example.xinychan.mygithubkt.network.entities.AuthorizationRsp
import com.example.xinychan.mygithubkt.network.retrofit
import com.example.xinychan.mygithubkt.settings.Configs
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path
import rx.Observable

/**
 * 鉴权请求相关接口
 */
interface AuthApi {

    /**
     * 创建授权
     */
    @PUT("/authorizations/clients/${Configs.Account.clientId}/{fingerPrint}")
    fun createAuthorization(
        @Body req: AuthorizationReq,
        @Path("fingerPrint") fingerPrint: String = Configs.Account.fingerPrint
    ): Observable<AuthorizationRsp>

    /**
     * 删除授权
     */
    @DELETE("/authorizations/{id}")
    fun deleteAuthorization(@Path("id") id: Int): Observable<Response<Any>>
}

object AuthServices : AuthApi by retrofit.create(AuthApi::class.java)