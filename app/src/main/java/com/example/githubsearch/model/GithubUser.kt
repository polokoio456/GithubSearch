package com.example.githubsearch.model

import com.google.gson.annotations.SerializedName

/*
{
  "login": "tom",
  "id": 748,
  "node_id": "MDQ6VXNlcjc0OA==",
  "avatar_url": "https://avatars1.githubusercontent.com/u/748?v=4",
  "gravatar_id": "",
  "url": "https://api.github.com/users/tom",
  "html_url": "https://github.com/tom",
  "followers_url": "https://api.github.com/users/tom/followers",
  "following_url": "https://api.github.com/users/tom/following{/other_user}",
  "gists_url": "https://api.github.com/users/tom/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/tom/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/tom/subscriptions",
  "organizations_url": "https://api.github.com/users/tom/orgs",
  "repos_url": "https://api.github.com/users/tom/repos",
  "events_url": "https://api.github.com/users/tom/events{/privacy}",
  "received_events_url": "https://api.github.com/users/tom/received_events",
  "type": "User",
  "site_admin": false,
  "score": 663.4386
}
 */
data class GithubUser(
    @SerializedName("login") val name: String,
    @SerializedName("avatar_url") val avatar: String
)