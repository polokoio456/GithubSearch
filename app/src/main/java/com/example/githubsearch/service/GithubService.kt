package com.example.githubsearch.service

import com.example.githubsearch.model.GithubSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("/search/users?&per_page=20")
    fun getUsers(@Query("q") keyword: String, @Query("page") page: Int): Call<GithubSearchResult>
}