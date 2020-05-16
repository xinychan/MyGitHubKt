package com.example.xinychan.mygithubkt.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.view.common.CommonSinglePageFragment

class MyIssueFragment : CommonSinglePageFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_issue, null)
    }
}