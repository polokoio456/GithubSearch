package com.example.githubsearch.model

import com.google.gson.annotations.SerializedName

/*
{
  "total_count": 95771,
  "incomplete_results": false,
  "items": []
}
 */
data class GithubSearchResult(
    @SerializedName("total_count")
    val totalCount: String,
    @SerializedName("incomplete_results")
    val incompleteResult: String,
    @SerializedName("items")
    val userList: List<GithubUser>
)