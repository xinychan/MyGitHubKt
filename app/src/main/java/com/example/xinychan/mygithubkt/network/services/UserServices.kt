package com.example.xinychan.mygithubkt.network.services

import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.network.retrofit
import retrofit2.adapter.rxjava.GitHubPaging
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * 用户数据请求相关接口
 */
interface UserApi {

    @GET("/user")
    fun getAuthenticatedUser(): Observable<User>

    @GET("/users")
    fun allUsers(
        @Query("since") since: Int,
        @Query("per_page") per_page: Int = 20
    ): Observable<GitHubPaging<User>>

    @GET("/users/{login}/following")
    fun following(
        @Path("login") login: String,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 20
    ): Observable<GitHubPaging<User>>

    @GET("/users/{login}/followers")
    fun followers(
        @Path("login") login: String,
        @Query("page") page: Int = 1
    ): Observable<GitHubPaging<User>>

}

object UserServices : UserApi by retrofit.create(UserApi::class.java)