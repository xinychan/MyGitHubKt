package com.example.xinychan.mygithubkt.view.fragments.subfragments

import com.example.xinychan.mygithubkt.network.entities.Issue
import com.example.xinychan.mygithubkt.presenter.MyIssuePresenter
import com.example.xinychan.mygithubkt.view.common.CommonListFragment

class MyIssueListFragment : CommonListFragment<Issue, MyIssuePresenter>() {
    companion object {
        const val REPOSITORY_NAME = "repository_name"
        const val OWNER_LOGIN = "owner_login"
    }

    override val adapter = IssueListAdapter()
}