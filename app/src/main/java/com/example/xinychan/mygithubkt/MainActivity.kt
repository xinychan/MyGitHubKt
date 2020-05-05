package com.example.xinychan.mygithubkt

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.xinychan.common.ext.no
import com.example.xinychan.common.ext.otherwise
import com.example.xinychan.common.log.logger
import com.example.xinychan.mygithubkt.model.account.AccountManager
import com.example.xinychan.mygithubkt.model.account.OnAccountStateChangeListener
import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.network.services.RepositoryService
import com.example.xinychan.mygithubkt.utils.doOnLayoutAvailable
import com.example.xinychan.mygithubkt.utils.format
import com.example.xinychan.mygithubkt.utils.loadWithGlide
import com.example.xinychan.mygithubkt.utils.showFragment
import com.example.xinychan.mygithubkt.view.LoginActivity
import com.example.xinychan.mygithubkt.view.fragments.AboutFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.util.*

class MainActivity : AppCompatActivity(), OnAccountStateChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        title = "About"

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

        showFragment(R.id.fragmentContainer, AboutFragment::class.java)

        RepositoryService.listRepositoriesOfUser("enbandari", 2)
            .subscribe({
                logger.debug("Paging: hasNext=${it.hasNext}, hasPrev=${it.hasPrev}")
            }, {
                it.printStackTrace()
            })

        RepositoryService.allRepositories(2, "pushed:<" + Date().format("yyyy-MM-dd"))
            .subscribe( {
                logger.debug("Paging: hasNext=${it.paging.hasNext}, hasPrev=${it.paging.hasPrev}")
            }, {
                it.printStackTrace()
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        AccountManager.onAccountStateChangeListeners.remove(this)
    }

    private fun initNavigationView() {
        AccountManager.currentUser?.let {
            updateNavigationView(it)
        } ?: clearNavigationView()
        initNavigationHeaderEvent()
    }

    private fun initNavigationHeaderEvent() {
        navigationView.doOnLayoutAvailable {
            navigationHeader.setOnClickListener {
                AccountManager.isLoggedIn().no {
                    startLoginActivity()
                }.otherwise {
                    AccountManager.logout().subscribe(
                        {
                            Toast.makeText(this, "注销成功", Toast.LENGTH_SHORT).show()
                        }, {
                            it.printStackTrace()
                        }
                    )
                }
            }
        }
    }

    private fun startLoginActivity() {
        val intent: Intent = Intent()
        intent.setClass(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun updateNavigationView(user: User) {
        navigationView.doOnLayoutAvailable {
            usernameView.text = user.login
            emailView.text = user.email ?: ""
            avatarView.loadWithGlide(user.avatar_url, user.login.first())
        }
    }

    private fun clearNavigationView() {
        navigationView.doOnLayoutAvailable {
            usernameView.text = "请登录"
            emailView.text = ""
            avatarView.setImageResource(R.drawable.ic_github)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onLogin(user: User) {
        updateNavigationView(user)
    }

    override fun onLogout() {
        clearNavigationView()
    }
}
