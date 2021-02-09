package com.example.githubsearch.module

import com.example.githubsearch.ui.model.SearchRepository
import com.example.githubsearch.ui.model.SearchRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<SearchRepository> { SearchRepositoryImpl(get()) }
}