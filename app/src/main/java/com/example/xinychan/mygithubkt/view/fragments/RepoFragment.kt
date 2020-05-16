package com.example.xinychan.mygithubkt.view.fragments

import android.os.Bundle
import com.example.xinychan.mygithubkt.model.account.AccountManager
import com.example.xinychan.mygithubkt.view.common.CommonViewPagerFragment
import com.example.xinychan.mygithubkt.view.config.FragmentPage

class RepoFragment : CommonViewPagerFragment() {
    override fun getFragmentPages(): List<FragmentPage> {
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