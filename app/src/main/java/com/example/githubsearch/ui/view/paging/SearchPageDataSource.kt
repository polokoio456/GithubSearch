package com.example.githubsearch.ui.view.paging

import androidx.paging.PageKeyedDataSource
import com.example.githubsearch.bus.NetStatusBus
import com.example.githubsearch.helper.RetrofitGenerator
import com.example.githubsearch.model.GithubUser
import com.example.githubsearch.service.GithubService

class SearchPageDataSource(
    private val keyword: String
): PageKeyedDataSource<Int, GithubUser>() {

    private var networkWrong: Boolean = false

    init {
        NetStatusBus.
                relay().
                subscribe {
                    networkWrong = when (it) {
                        is NetStatusBus.Status.Connected -> false
                        is NetStatusBus.Status.Disconnected -> true
                    }
                }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GithubUser>
    ) {
        if (networkWrong) {
            return
        }

        val response = RetrofitGenerator.getRetrofit().create(GithubService::class.java).getUsers(keyword, 1).execute()
        if (response.isSuccessful) {
            val data = response.body()
            data?.let {
                callback.onResult(it.userList, null, 2)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {
        if (networkWrong) {
            return
        }

        val response = RetrofitGenerator.getRetrofit().create(GithubService::class.java).getUsers(keyword, params.key).execute()
        if (response.isSuccessful) {
            val data = response.body()
            data?.let {
                callback.onResult(it.userList, params.key + 1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {

    }
}