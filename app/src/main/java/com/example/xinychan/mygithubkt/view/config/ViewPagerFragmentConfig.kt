package com.example.xinychan.mygithubkt.view.config

import android.support.v4.app.Fragment

interface ViewPagerFragmentConfig {
    fun getFragmentPages(): List<FragmentPage>
}

data class FragmentPage(val fragment: Fragment, val title: String)
