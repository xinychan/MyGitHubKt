package com.example.xinychan.mygithubkt.view.fragments.subfragments

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.network.entities.Issue
import com.example.xinychan.mygithubkt.utils.CommonListAdapter
import com.example.xinychan.mygithubkt.utils.githubTimeToDate
import com.example.xinychan.mygithubkt.utils.htmlText
import com.example.xinychan.mygithubkt.utils.view
import kotlinx.android.synthetic.main.item_issue.view.*

open class IssueListAdapter : CommonListAdapter<Issue>(R.layout.item_issue) {
    override fun onItemClicked(itemView: View, issue: Issue) {
        // todo
    }

    override fun onBindData(viewHolder: RecyclerView.ViewHolder, issue: Issue) {
        viewHolder.itemView.apply {
            val imageResource =
                if (issue.state == "open") R.drawable.ic_issue_open else R.drawable.ic_issue_closed
            iconView.setImageResource(imageResource)
            titleView.text = issue.title
            timeView.text = githubTimeToDate(issue.created_at).view()
            bodyView.htmlText = issue.body_html
            commentCount.text = issue.comments.toString()
        }
    }
}