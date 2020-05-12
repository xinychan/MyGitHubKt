package com.example.xinychan.mygithubkt.view.common

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.xinychan.mvp.impl.BaseFragment
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.model.page.ListPage
import com.example.xinychan.mygithubkt.utils.CommonListAdapter
import com.example.xinychan.mygithubkt.view.widget.ErrorInfoView
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter
import kotlinx.android.synthetic.main.list.*
import retrofit2.adapter.rxjava.GitHubPaging

abstract class CommonListFragment<DataType, out Presenter : CommonListPresenter<DataType, CommonListFragment<DataType, Presenter>>> :
    BaseFragment<Presenter>() {
    protected abstract val adapter: CommonListAdapter<DataType>

    protected val errorInfoView by lazy {
        ErrorInfoView(rootView)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshView.setColorSchemeResources(
            R.color.google_red,
            R.color.google_yellow,
            R.color.google_green,
            R.color.google_blue
        )

        recyclerView.adapter = LuRecyclerViewAdapter(adapter)
        recyclerView.setLoadMoreEnabled(true)
        recyclerView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()

        refreshView.isRefreshing = true

        recyclerView.setOnLoadMoreListener(presenter::loadMore)

        refreshView.setOnRefreshListener { presenter::refreshData }
        presenter.initData()
    }

    fun setLoadMoreEnable(isEnabled: Boolean) {
        recyclerView.setLoadMoreEnabled(isEnabled)
    }

    fun onDataInit(data: GitHubPaging<DataType>) {
        adapter.data.clear()
        adapter.data.addAll(data)
        recyclerView.setNoMore(data.isLast)
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        refreshView.isRefreshing = false
        dismissError()
    }

    fun onDataRefresh(data: GitHubPaging<DataType>) {
        onDataInit(data)
    }

    fun onDataInitWithNothing() {
        showError("No Data.")
        recyclerView.setNoMore(true)
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        refreshView.isRefreshing = false
        errorInfoView.isClickable = false
    }

    fun onDataInitWithError(error: String) {
        showError(error)
        errorInfoView.setOnClickListener { presenter.initData() }
    }

    fun onDataRefreshWithError(error: String) {
        if (adapter.data.isEmpty()) {
            showError(error)
            errorInfoView.setOnClickListener { presenter.initData() }
        } else {
            Toast.makeText(this.context, error, Toast.LENGTH_SHORT).show()
        }
    }

    fun onMoreDataLoaded(data: GitHubPaging<DataType>) {
        adapter.data.update(data)
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        recyclerView.setNoMore(data.isLast)
        dismissError()
    }

    fun onMoreDataLoadedWithError(error: String) {
        showError(error)
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        errorInfoView.setOnClickListener { presenter.initData() }
    }

    protected open fun showError(error: String) {
        errorInfoView.show(error)
    }

    protected open fun dismissError() {
        errorInfoView.dismiss()
    }
}