package com.example.xinychan.mygithubkt.network.entities

import com.example.xinychan.common.anno.Poko
import retrofit2.adapter.rxjava.PagingWrapper

/**
 * 搜索请求接口返回值实体类
 */
@Poko
data class SearchRepositories(
    var total_count: Int,
    var incomplete_results: Boolean,
    var items: List<Repository>
) : PagingWrapper<Repository>() {

    override fun getElements() = items

}