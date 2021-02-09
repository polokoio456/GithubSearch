package com.example.githubsearch.ui.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.githubsearch.model.GithubUser
import io.reactivex.disposables.CompositeDisposable

interface SearchRepository {
    fun search(keyword: String, compositeDisposable: CompositeDisposable): LiveData<PagedList<GithubUser>>
}