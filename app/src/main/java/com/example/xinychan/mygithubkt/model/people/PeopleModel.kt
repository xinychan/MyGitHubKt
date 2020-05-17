package com.example.xinychan.mygithubkt.model.people

import com.example.xinychan.mygithubkt.model.page.ListPage
import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.network.services.UserServices
import retrofit2.adapter.rxjava.GitHubPaging
import rx.Observable

class PeoplePageParams(val type: String, val login: String?)

class PeoplePage(val params: PeoplePageParams) : ListPage<User>() {

    enum class Type {
        FOLLOWER, FOLLOWING, ALL
    }

    override fun getData(page: Int): Observable<GitHubPaging<User>> {
        return when (Type.valueOf(params.type)) {
            PeoplePage.Type.FOLLOWER -> UserServices.followers(params.login!!, page = page)
            PeoplePage.Type.FOLLOWING -> UserServices.following(params.login!!, page = page)
            PeoplePage.Type.ALL -> UserServices.allUsers(data.since)
        }
    }

}
