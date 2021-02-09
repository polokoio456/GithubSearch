package com.example.githubsearch.ui.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.githubsearch.model.GithubUser
import com.example.githubsearch.ui.model.SearchRepository
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel(private val repository: SearchRepository): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun onSearchClick(keyword: String): LiveData<PagedList<GithubUser>> {
        return repository.search(keyword, compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}