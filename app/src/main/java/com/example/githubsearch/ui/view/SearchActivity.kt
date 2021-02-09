package com.example.githubsearch.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearch.databinding.ActivitySearchBinding
import com.example.githubsearch.ui.view.adapter.SearchAdapter
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class SearchActivity : AppCompatActivity() {
    private val viewModel by viewModel<SearchViewModel>()

    private val adapter by inject<SearchAdapter>()

    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        RxTextView.textChanges(binding.editSearchBar)
            .throttleLast(1500L, TimeUnit.MILLISECONDS)
            .filter { it.isNotEmpty() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Nie", "Search -> $it")
                viewModel.onSearchClick(it.toString()).observe(this, { list ->
                    adapter.submitList(list)
                })
            }.apply {
                compositeDisposable.add(this)
            }

        initView()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun initView() {
        binding.recyclerView.overScrollMode = View.OVER_SCROLL_NEVER
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}
