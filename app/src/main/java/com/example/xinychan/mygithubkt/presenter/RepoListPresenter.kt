package com.example.xinychan.mygithubkt.presenter

import com.example.xinychan.mygithubkt.model.repo.RepoListPage
import com.example.xinychan.mygithubkt.network.entities.Repository
import com.example.xinychan.mygithubkt.view.common.CommonListPresenter
import com.example.xinychan.mygithubkt.view.fragments.RepoListFragment

class RepoListPresenter : CommonListPresenter<Repository, RepoListFragment>() {
    override val listPage by lazy {
        RepoListPage(view.user)
    }
}