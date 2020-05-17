package com.example.xinychan.mygithubkt.view.fragments.subfragments

import com.bennyhuo.tieguanyin.annotations.FragmentBuilder
import com.bennyhuo.tieguanyin.annotations.Optional
import com.bennyhuo.tieguanyin.annotations.Required
import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.presenter.PeopleListPresenter
import com.example.xinychan.mygithubkt.view.common.CommonListFragment

@FragmentBuilder
class PeopleListFragment : CommonListFragment<User, PeopleListPresenter>() {
    @Optional
    lateinit var login: String

    @Required
    lateinit var type: String

    override val adapter = PeopleListAdapter()
}