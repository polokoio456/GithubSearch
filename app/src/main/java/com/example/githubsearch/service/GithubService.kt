package com.example.githubsearch.service

import com.example.githubsearch.model.GithubSearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("/search/users?&per_page=20")
    fun getUsers(@Query("q") keyword: String, @Query("page") page: Int): Single<GithubSearchResult>
}