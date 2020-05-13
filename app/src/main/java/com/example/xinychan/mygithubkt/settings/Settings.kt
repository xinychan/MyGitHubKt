package com.example.xinychan.mygithubkt.settings

import com.example.xinychan.mygithubkt.AppContext
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.model.account.AccountManager
import com.example.xinychan.mygithubkt.utils.pref

object Settings {

    var lastPage: Int
        get() = if (lastPageIdString.isEmpty()) {
            0
        } else {
            AppContext.resources.getIdentifier(lastPageIdString, "id", AppContext.packageName)
        }
        set(value) {
            lastPageIdString = AppContext.resources.getResourceEntryName(value)
        }

    val defaultPage: Int
        get() = if (AccountManager.isLoggedIn()) {
            defaultPageForUser
        } else {
            defaultPageForVisitor
        }

    private var defaultPageForUser by pref(R.id.navRepos)

    private var defaultPageForVisitor by pref(R.id.navRepos)

    private var lastPageIdString by pref("")
}