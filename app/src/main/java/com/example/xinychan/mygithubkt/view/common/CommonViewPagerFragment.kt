package com.example.xinychan.mygithubkt.view.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xinychan.mygithubkt.MainActivity
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.model.account.AccountManager
import com.example.xinychan.mygithubkt.model.account.OnAccountStateChangeListener
import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.view.config.ViewPagerFragmentConfig

abstract class CommonViewPagerFragment : Fragment(), ViewPagerFragmentConfig,
    OnAccountStateChangeListener {

    private val viewPageAdapter by lazy {
        CommonViewPageAdapter(childFragmentManager)
    }

    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = View.inflate(context, R.layout.fragment_common_view_page, null)
        viewPager = view.findViewById(R.id.common_view_pager)
        viewPager.adapter = viewPageAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).actionBarController.setupWithViewPager(viewPager)
        viewPageAdapter.fragmentPages.addAll(
            if (AccountManager.isLoggedIn()) {
                getFragmentPagesLoggedIn()
            } else {
                getFragmentPagesNotLoggedIn()
            }
        )
    }

    override fun onLogin(user: User) {
        viewPageAdapter.fragmentPages.clear()
        viewPageAdapter.fragmentPages.addAll(getFragmentPagesLoggedIn())
    }

    override fun onLogout() {
        viewPageAdapter.fragmentPages.clear()
        viewPageAdapter.fragmentPages.addAll(getFragmentPagesNotLoggedIn())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AccountManager.onAccountStateChangeListeners.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AccountManager.onAccountStateChangeListeners.remove(this)
    }
}