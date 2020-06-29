package com.example.githubsearch.ui.viewmodel

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.githubsearch.model.GithubUser
import com.example.githubsearch.ui.model.SearchModel
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private var model = SearchModel()
    private var searchView: SearchView? = null
    private var keyword = ""

    private var networkWrong: Boolean = false

    fun init(searchView: SearchView) {
        this.searchView = searchView
    }

    fun getSearchTextWatcher(): TextWatcher {
        return object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val word = s.toString()
                keyword = word
            }

            override fun afterTextChanged(s: Editable) {

            }
        }
    }

    fun onSearchClick() {
        if (networkWrong) {
            searchView?.onNetworkError()
            return
        }

        if (keyword.isNotEmpty()) {
            searchView?.onSearch(model.search(keyword, compositeDisposable))
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    interface SearchView {
        fun onSearch(data: LiveData<PagedList<GithubUser>>?)
        fun onNetworkError()
    }
}