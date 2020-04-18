package com.example.xinychan.mygithubkt.network.services

import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.network.retrofit
import retrofit2.http.GET
import rx.Observable

/**
 * 用户数据请求相关接口
 */
interface UserApi {

    @GET("/user")
    fun getAuthenticatedUser(): Observable<User>
}

object UserServices : UserApi by retrofit.create(UserApi::class.java)