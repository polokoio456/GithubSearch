package com.example.githubsearch.ui.model

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.githubsearch.model.GithubUser
import com.example.githubsearch.ui.view.paging.SearchPageDataSourceFactory

class SearchModel {

    fun search(keyword: String): LiveData<PagedList<GithubUser>> {
        return LivePagedListBuilder(
            SearchPageDataSourceFactory(keyword),
            PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(false)
                .build()
        ).setInitialLoadKey(FIRST_PAGE).build()
    }

    companion object {
        private const val FIRST_PAGE = 1
        private const val PAGE_SIZE = 20
    }
}