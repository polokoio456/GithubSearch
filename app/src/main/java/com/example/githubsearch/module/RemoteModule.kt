package com.example.githubsearch.module

import com.example.githubsearch.helper.RetrofitGenerator
import com.example.githubsearch.service.GithubService
import org.koin.dsl.module

val remoteModule = module {
    single { RetrofitGenerator.getRetrofit().create(GithubService::class.java) }
}