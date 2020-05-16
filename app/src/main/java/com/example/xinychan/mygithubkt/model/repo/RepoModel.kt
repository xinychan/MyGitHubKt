package com.example.xinychan.mygithubkt.model.repo

import com.example.xinychan.mygithubkt.model.page.ListPage
import com.example.xinychan.mygithubkt.network.entities.Repository
import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.network.services.RepositoryService
import com.example.xinychan.mygithubkt.utils.format
import retrofit2.adapter.rxjava.GitHubPaging
import rx.Observable
import java.util.*

class RepoListPage(val owner: User?) : ListPage<Repository>() {
    override fun getData(page: Int): Observable<GitHubPaging<Repository>> {
        // 未登录则查询所有的仓库；已登录则查询登录用户的仓库
        return if (owner == null) {
            RepositoryService.allRepositories(page, "pushed:<" + Date().format("yyyy-MM-dd"))
                .map { it.paging }
        } else {
            RepositoryService.listRepositoriesOfUser(owner.login, page)
        }
    }
}