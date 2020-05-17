package com.example.xinychan.mygithubkt.view.fragments

import android.os.Bundle
import com.example.xinychan.mygithubkt.model.account.AccountManager
import com.example.xinychan.mygithubkt.view.common.CommonViewPagerFragment
import com.example.xinychan.mygithubkt.view.config.FragmentPage
import com.example.xinychan.mygithubkt.view.fragments.subfragments.RepoListFragment
import com.example.xinychan.mygithubkt.view.fragments.subfragments.RepoListFragmentBuilder

class RepoFragment : CommonViewPagerFragment() {

    override fun getFragmentPagesNotLoggedIn(): List<FragmentPage> {
        return listOf(FragmentPage(RepoListFragment(), "All"))
    }

    override fun getFragmentPagesLoggedIn(): List<FragmentPage> {
        return listOf(
            FragmentPage(RepoListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(
                        RepoListFragmentBuilder.OPTIONAL_USER,
                        AccountManager.currentUser
                    )
                }
            }, "My"),
            FragmentPage(RepoListFragment(), "All")
        )
    }

}