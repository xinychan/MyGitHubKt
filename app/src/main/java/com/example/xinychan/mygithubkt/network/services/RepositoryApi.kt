package com.example.xinychan.mygithubkt.network.services

import com.example.xinychan.mygithubkt.network.entities.Repository
import com.example.xinychan.mygithubkt.network.retrofit
import retrofit2.adapter.rxjava.GitHubPaging
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface RepositoryApi {

    @GET("/users/{owner}/repos?type=all")
    fun listRepositoriesOfUser(
        @Path("owner") owner: String,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 20
    ): Observable<GitHubPaging<Repository>>
}

object RepositoryService : RepositoryApi by retrofit.create(RepositoryApi::class.java)