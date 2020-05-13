package com.example.xinychan.mygithubkt

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.xinychan.common.ext.no
import com.example.xinychan.common.ext.otherwise
import com.example.xinychan.common.ext.yes
import com.example.xinychan.mygithubkt.model.account.AccountManager
import com.example.xinychan.mygithubkt.model.account.OnAccountStateChangeListener
import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.utils.afterClosed
import com.example.xinychan.mygithubkt.utils.showFragment
import com.example.xinychan.mygithubkt.view.LoginActivity
import com.example.xinychan.mygithubkt.view.config.NavViewItem
import com.example.xinychan.mygithubkt.view.widget.NavigationController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), OnAccountStateChangeListener {

    private val navigationController by lazy {
        NavigationController(navigationView, ::onNavItemChanged, ::handleNavigationHeaderClickEvent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // 实现 ActionBar 与 抽屉联动
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.setDrawerListener(toggle)
        toggle.syncState()

        initNavigationView()

        AccountManager.onAccountStateChangeListeners.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AccountManager.onAccountStateChangeListeners.remove(this)
    }

    private fun initNavigationView() {
        AccountManager.isLoggedIn()
            .yes {
                navigationController.useLoginLayout()
            }
            .otherwise {
                navigationController.useNoLoginLayout()
            }
        navigationController.selectProperItem()
    }

    override fun onLogin(user: User) {
        navigationController.useLoginLayout()
    }

    override fun onLogout() {
        navigationController.useNoLoginLayout()
    }

    private fun onNavItemChanged(navViewItem: NavViewItem) {
        drawer_layout.afterClosed {
            showFragment(R.id.fragmentContainer, navViewItem.fragmentClass, navViewItem.arguements)
            title = navViewItem.title
        }
    }

    private fun handleNavigationHeaderClickEvent() {
        AccountManager.isLoggedIn().no {
            startLoginActivity()
        }.otherwise {
            AccountManager
                .logout()
                .subscribe({
                    Toast.makeText(this, "注销成功", Toast.LENGTH_SHORT).show()
                }, {
                    it.printStackTrace()
                })
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun startLoginActivity() {
        val intent: Intent = Intent()
        intent.setClass(this, LoginActivity::class.java)
        startActivity(intent)
    }


}
