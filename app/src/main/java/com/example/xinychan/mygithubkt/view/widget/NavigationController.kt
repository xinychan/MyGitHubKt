package com.example.xinychan.mygithubkt.view.widget

import android.support.design.widget.NavigationView
import android.view.MenuItem
import com.example.xinychan.common.log.logger
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.model.account.AccountManager
import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.settings.Settings
import com.example.xinychan.mygithubkt.utils.doOnLayoutAvailable
import com.example.xinychan.mygithubkt.utils.loadWithGlide
import com.example.xinychan.mygithubkt.utils.selectItem
import com.example.xinychan.mygithubkt.view.config.NavViewItem
import kotlinx.android.synthetic.main.nav_header_main.view.*

class NavigationController(
    private val navigationView: NavigationView,
    private val onNavItemChanged: (NavViewItem) -> Unit,
    private val onHeaderClick: () -> Unit
) : NavigationView.OnNavigationItemSelectedListener {

    init {
        navigationView.setNavigationItemSelectedListener(this)
    }

    private var currentItem: NavViewItem? = null

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navigationView.apply {
            Settings.lastPage = item.itemId
            val navItem = NavViewItem[item.itemId]
            onNavItemChanged(navItem)
        }
        return true
    }

    fun useLoginLayout() {
        navigationView.menu.clear()
        navigationView.inflateMenu(R.menu.activity_main_drawer) //inflate new items.
        onUpdate(AccountManager.currentUser)
        selectProperItem()
    }

    fun useNoLoginLayout() {
        navigationView.menu.clear()
        navigationView.inflateMenu(R.menu.activity_main_drawer_no_logged_in) //inflate new items.
        onUpdate(AccountManager.currentUser)
        selectProperItem()
    }

    private fun onUpdate(user: User?) {
        navigationView.doOnLayoutAvailable {
            navigationView.apply {
                usernameView.text = user?.login ?: "请登录"
                emailView.text = user?.email ?: "bennyhuo@kotliner.cn"
                if (user == null) {
                    avatarView.setImageResource(R.drawable.ic_github)
                } else {
                    avatarView.loadWithGlide(user.avatar_url, user.login.first())
                }
                navigationHeader.setOnClickListener { onHeaderClick() }
            }
        }
    }

    fun selectProperItem() {
        logger.debug("selectProperItem")
        navigationView.doOnLayoutAvailable {
            logger.debug("selectProperItem onLayout: $currentItem")
            ((currentItem?.let { NavViewItem[it] } ?: Settings.lastPage)
                .takeIf { navigationView.menu.findItem(it) != null }
                ?: run { Settings.defaultPage })
                .let(navigationView::selectItem)
        }
    }
}