package com.example.xinychan.mygithubkt.model.issue

import com.example.xinychan.mygithubkt.model.page.ListPage
import com.example.xinychan.mygithubkt.network.entities.Issue
import com.example.xinychan.mygithubkt.network.services.IssueService
import retrofit2.adapter.rxjava.GitHubPaging
import rx.Observable

class MyIssuePage : ListPage<Issue>() {
    override fun getData(page: Int): Observable<GitHubPaging<Issue>> {
        return IssueService.listIssuesOfAuthenticatedUser(page = page)
    }
}