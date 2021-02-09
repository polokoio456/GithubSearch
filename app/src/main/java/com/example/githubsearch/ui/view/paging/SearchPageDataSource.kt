package com.example.githubsearch.ui.view.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.githubsearch.helper.RetrofitGenerator
import com.example.githubsearch.model.GithubUser
import com.example.githubsearch.service.GithubService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SearchPageDataSource(
    private val keyword: String,
    private val compositeDisposable: CompositeDisposable,
    private val githubService: GithubService
): PageKeyedDataSource<Int, GithubUser>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GithubUser>) {
        githubService
            .getUsers(keyword, 1)
            .subscribeOn(Schedulers.io())
            .subscribe({
                callback.onResult(it.userList, null, 2)
            }, {
                Log.e("Nie", "loadInitial : $it")
            }).let { compositeDisposable.add(it) }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {
        githubService
            .getUsers(keyword, params.key)
            .retryWhen { it.delay(1, TimeUnit.SECONDS) }
            .subscribeOn(Schedulers.io())
            .subscribe({
                callback.onResult(it.userList, params.key + 1)
            }, {
                Log.e("Nie", "loadAfter : $it")
            }).let { compositeDisposable.add(it) }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {
        // Do not need to handle this.
    }
}