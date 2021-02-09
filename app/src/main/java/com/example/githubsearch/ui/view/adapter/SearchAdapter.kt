package com.example.githubsearch.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubsearch.R
import com.example.githubsearch.model.GithubUser
import java.util.*

class SearchAdapter: PagedListAdapter<GithubUser, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubUser>() {

            override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
                return oldItem.avatar == newItem.avatar
            }

            override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
                return oldItem == newItem
            }
        }
    }

    class SearchViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var thumbImageView: ImageView = itemView.findViewById(R.id.avatar)
        var nameTextView: TextView = itemView.findViewById(R.id.name)

        fun onBind(item: GithubUser) {
            Glide.with(itemView.context)
                .load(item.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(thumbImageView)

            nameTextView.text = String.format(
                Locale.getDefault(),
                itemView.context.getString(R.string.item_search_name),
                item.name
            )
        }
    }
}