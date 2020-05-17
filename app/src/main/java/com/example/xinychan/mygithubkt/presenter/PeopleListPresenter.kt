package com.example.xinychan.mygithubkt.presenter

import com.example.xinychan.mygithubkt.model.page.ListPage
import com.example.xinychan.mygithubkt.model.people.PeoplePage
import com.example.xinychan.mygithubkt.model.people.PeoplePageParams
import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.view.common.CommonListPresenter
import com.example.xinychan.mygithubkt.view.fragments.subfragments.PeopleListFragment

class PeopleListPresenter : CommonListPresenter<User, PeopleListFragment>() {

    override val listPage: ListPage<User> by lazy {
        PeoplePage(PeoplePageParams(view.type, view.login))
    }

}