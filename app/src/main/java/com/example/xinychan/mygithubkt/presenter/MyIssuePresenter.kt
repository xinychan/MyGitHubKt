package com.example.xinychan.mygithubkt.presenter

import com.example.xinychan.mygithubkt.model.issue.MyIssuePage
import com.example.xinychan.mygithubkt.network.entities.Issue
import com.example.xinychan.mygithubkt.view.common.CommonListPresenter
import com.example.xinychan.mygithubkt.view.fragments.subfragments.MyIssueListFragment

class MyIssuePresenter : CommonListPresenter<Issue, MyIssueListFragment>() {
    override val listPage = MyIssuePage()
}