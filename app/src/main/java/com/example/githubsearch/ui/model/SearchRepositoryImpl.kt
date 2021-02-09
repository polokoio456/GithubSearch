package com.example.githubsearch.ui.model

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.githubsearch.model.GithubUser
import com.example.githubsearch.ui.view.paging.SearchPageDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class SearchRepositoryImpl : SearchRepository {
    companion object {
        private const val FIRST_PAGE = 1
        private const val PAGE_SIZE = 20
    }

    override fun search(keyword: String, compositeDisposable: CompositeDisposable): LiveData<PagedList<GithubUser>> {
        return LivePagedListBuilder(
            SearchPageDataSourceFactory(keyword, compositeDisposable),
            PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(false)
                .build()
        ).setInitialLoadKey(FIRST_PAGE).build()
    }
}