package com.example.githubsearch.module

import com.example.githubsearch.ui.view.adapter.SearchAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory { SearchAdapter() }
}
