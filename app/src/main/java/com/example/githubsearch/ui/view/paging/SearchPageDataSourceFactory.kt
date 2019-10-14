package com.example.githubsearch.ui.view.paging

import androidx.paging.DataSource
import com.example.githubsearch.model.GithubUser

class SearchPageDataSourceFactory(
    private val keyword: String
): DataSource.Factory<Int, GithubUser>() {

    override fun create(): DataSource<Int, GithubUser> {
        return SearchPageDataSource(keyword)
    }
}