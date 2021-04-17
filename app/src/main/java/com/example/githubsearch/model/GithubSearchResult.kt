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
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResult: Boolean,
    @SerializedName("items")
    val userList: List<GithubUser>
)