package com.example.xinychan.mygithubkt.view.fragments

import android.os.Bundle
import com.example.xinychan.mygithubkt.model.account.AccountManager
import com.example.xinychan.mygithubkt.model.people.PeoplePage
import com.example.xinychan.mygithubkt.view.common.CommonViewPagerFragment
import com.example.xinychan.mygithubkt.view.config.FragmentPage
import com.example.xinychan.mygithubkt.view.fragments.subfragments.PeopleListFragment
import com.example.xinychan.mygithubkt.view.fragments.subfragments.PeopleListFragmentBuilder

class PeopleFragment : CommonViewPagerFragment() {
    override fun getFragmentPagesNotLoggedIn(): List<FragmentPage> {
        return listOf(FragmentPage(PeopleListFragment().also {
            it.arguments = Bundle().apply {
                putString(PeopleListFragmentBuilder.REQUIRED_TYPE, PeoplePage.Type.ALL.name)
            }
        }, "All"))
    }

    override fun getFragmentPagesLoggedIn(): List<FragmentPage> =
        listOf(
            FragmentPage(PeopleListFragment().also {
                it.arguments = Bundle().apply {
                    putString(
                        PeopleListFragmentBuilder.OPTIONAL_LOGIN,
                        AccountManager.currentUser?.login
                    )
                    putString(
                        PeopleListFragmentBuilder.REQUIRED_TYPE,
                        PeoplePage.Type.FOLLOWER.name
                    )
                }
            }, "Followers"),
            FragmentPage(PeopleListFragment().also {
                it.arguments = Bundle().apply {
                    putString(
                        PeopleListFragmentBuilder.OPTIONAL_LOGIN,
                        AccountManager.currentUser!!.login
                    )
                    putString(
                        PeopleListFragmentBuilder.REQUIRED_TYPE,
                        PeoplePage.Type.FOLLOWING.name
                    )
                }
            }, "Following"),
            FragmentPage(PeopleListFragment().also {
                it.arguments = Bundle().apply {
                    putString(PeopleListFragmentBuilder.REQUIRED_TYPE, PeoplePage.Type.ALL.name)
                }
            }, "All")
        )

}