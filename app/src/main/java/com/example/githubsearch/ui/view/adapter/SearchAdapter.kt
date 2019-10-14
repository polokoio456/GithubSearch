package com.example.githubsearch.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.R
import com.example.githubsearch.helper.ImageLoaderHelper
import com.example.githubsearch.model.GithubUser
import java.util.*

class SearchAdapter: PagedListAdapter<GithubUser, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view, parent.context)
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

    class SearchViewHolder(itemView: View, private val context: Context): RecyclerView.ViewHolder(itemView) {
        var thumbImageView: ImageView = itemView.findViewById(R.id.avatar)
        var nameTextView: TextView = itemView.findViewById(R.id.name)

        fun onBind(item: GithubUser) {
            ImageLoaderHelper.getInstance().loadImage(thumbImageView, item.avatar)

            nameTextView.text = String.format(
                Locale.getDefault(),
                context.getString(R.string.item_search_name),
                item.name
            )
        }
    }
}