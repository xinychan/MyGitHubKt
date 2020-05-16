package com.example.xinychan.mygithubkt.view.fragments

import com.bennyhuo.tieguanyin.annotations.FragmentBuilder
import com.bennyhuo.tieguanyin.annotations.Optional
import com.example.xinychan.mygithubkt.network.entities.Repository
import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.presenter.RepoListPresenter
import com.example.xinychan.mygithubkt.view.common.CommonListFragment

@FragmentBuilder
class RepoListFragment : CommonListFragment<Repository, RepoListPresenter>() {

    @Optional
    var user: User? = null

    override val adapter = RepoListAdapter()
}