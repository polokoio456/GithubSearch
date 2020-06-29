package com.example.githubsearch.ui.view.paging

import androidx.paging.DataSource
import com.example.githubsearch.model.GithubUser
import io.reactivex.disposables.CompositeDisposable

class SearchPageDataSourceFactory(
    private val keyword: String,
    private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, GithubUser>() {

    override fun create(): DataSource<Int, GithubUser> {
        return SearchPageDataSource(keyword, compositeDisposable)
    }
}