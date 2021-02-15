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
import com.example.githubsearch.databinding.ItemSearchBinding
import com.example.githubsearch.model.GithubUser
import java.util.*

class SearchAdapter: PagedListAdapter<GithubUser, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    class SearchViewHolder(private val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: GithubUser) {
            Glide.with(itemView.context)
                .load(item.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.avatar)

            binding.name.text = String.format(
                Locale.getDefault(),
                itemView.context.getString(R.string.item_search_name),
                item.name
            )
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubUser>() {
            override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser) = (oldItem.avatar == newItem.avatar)
            override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser) = (oldItem == newItem)
        }
    }
}