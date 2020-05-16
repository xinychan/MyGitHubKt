package com.example.xinychan.mygithubkt.view.fragments

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.network.entities.Repository
import com.example.xinychan.mygithubkt.utils.CommonListAdapter
import com.example.xinychan.mygithubkt.utils.loadWithGlide
import com.example.xinychan.mygithubkt.utils.toKilo
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoListAdapter : CommonListAdapter<Repository>(R.layout.item_repo) {
    override fun onBindData(viewHolder: RecyclerView.ViewHolder, repository: Repository) {
        viewHolder.itemView.apply {
            avatarView.loadWithGlide(repository.owner.avatar_url, repository.owner.login.first())
            repoNameView.text = repository.name
            descriptionView.text = repository.description
            langView.text = repository.language ?: "Unknown"
            starView.text = repository.stargazers_count.toKilo()
            forkView.text = repository.forks_count.toKilo()
        }
    }

    override fun onItemClicked(itemView: View, item: Repository) {
        //todo
    }

}