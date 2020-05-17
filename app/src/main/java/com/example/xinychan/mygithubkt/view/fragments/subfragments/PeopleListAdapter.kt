package com.example.xinychan.mygithubkt.view.fragments.subfragments

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.network.entities.User
import com.example.xinychan.mygithubkt.utils.CommonListAdapter
import com.example.xinychan.mygithubkt.utils.loadWithGlide
import kotlinx.android.synthetic.main.item_user.view.*

class PeopleListAdapter : CommonListAdapter<User>(R.layout.item_user) {
    override fun onItemClicked(itemView: View, item: User) {
        // todo
    }

    override fun onBindData(viewHolder: RecyclerView.ViewHolder, user: User) {
        viewHolder.itemView.apply {
            avatarView.loadWithGlide(user.avatar_url, user.login.first())
            nameView.text = user.login
        }
    }
}