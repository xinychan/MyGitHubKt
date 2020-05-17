package com.example.xinychan.mygithubkt.network.entities

import android.os.Parcelable
import com.example.xinychan.common.anno.Poko
import kotlinx.android.parcel.Parcelize

@Poko
@Parcelize
data class Issue(
    var url: String,
    var repository_url: String,
    var labels_url: String,
    var comments_url: String,
    var events_url: String,
    var html_url: String,
    var id: Int,
    var number: Int,
    var title: String,
    var user: BasicUser,
    var state: String,
    var locked: Boolean,
    var comments: Int,
    var created_at: String,
    var updated_at: String,
    var author_association: String,
    var body: String,
    var body_html: String,
    var labels: List<Labels>,
    var assignees: List<BasicUser>,
    var repository: Repository
) : Parcelable {
    @Poko
    @Parcelize
    data class Labels(
        var id: Int,
        var url: String,
        var name: String,
        var color: String,
        var default: Boolean
    ) : Parcelable
}