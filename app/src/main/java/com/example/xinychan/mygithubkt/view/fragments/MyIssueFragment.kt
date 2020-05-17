package com.example.xinychan.mygithubkt.view.fragments

import com.example.xinychan.mygithubkt.view.common.CommonViewPagerFragment
import com.example.xinychan.mygithubkt.view.config.FragmentPage
import com.example.xinychan.mygithubkt.view.fragments.subfragments.MyIssueListFragment

class MyIssueFragment : CommonViewPagerFragment() {
    override fun getFragmentPagesNotLoggedIn() = listOf(
        FragmentPage(MyIssueListFragment(), "My")
    )

    override fun getFragmentPagesLoggedIn(): List<FragmentPage> = listOf(
        FragmentPage(MyIssueListFragment(), "My")
    )
}