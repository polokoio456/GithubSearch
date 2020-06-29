package com.example.githubsearch.ui.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearch.R
import com.example.githubsearch.databinding.ActivitySearchBinding
import com.example.githubsearch.model.GithubUser
import com.example.githubsearch.ui.view.adapter.SearchAdapter
import com.example.githubsearch.ui.viewmodel.SearchViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchViewModel.SearchView {
    private var binding: ActivitySearchBinding? = null
    private var viewModel: SearchViewModel? = null
    private var adapter: SearchAdapter? = null

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewModel?.init(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding?.viewModel = viewModel

        initView()
        initAdapter()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun initView() {
        binding?.userList?.overScrollMode = View.OVER_SCROLL_NEVER

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.userList?.layoutManager = layoutManager

        binding?.searchBar?.setOnEditorActionListener(object : TextView.OnEditorActionListener {

            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel?.onSearchClick()
                    return true
                }

                return false
            }
        })
    }

    private fun initAdapter() {
        adapter = SearchAdapter()
        binding?.userList?.adapter = adapter
    }

    private fun showNetworkWrongToast() {
        Toast.makeText(this, getString(R.string.network_wrong), Toast.LENGTH_SHORT).show()
    }

    override fun onSearch(data: LiveData<PagedList<GithubUser>>?) {
        if (progressbar.visibility == View.VISIBLE) {
            return
        }

        progressbar.visibility = View.VISIBLE
        adapter?.submitList(null)

        if (data == null) {
            progressbar.visibility = View.GONE
            showNetworkWrongToast()
            return
        }

        data.observe(this, Observer {
            progressbar.visibility = View.GONE
            adapter?.submitList(it)
        })
    }

    override fun onNetworkError() {
        showNetworkWrongToast()
    }
}
