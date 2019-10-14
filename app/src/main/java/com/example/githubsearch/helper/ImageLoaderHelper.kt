package com.example.githubsearch.helper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageLoaderHelper {
    companion object {
        @Volatile
        private var INSTANCE: ImageLoaderHelper? = null

        fun getInstance(): ImageLoaderHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ImageLoaderHelper().also { INSTANCE = it }
            }
    }

    fun loadImage(view: ImageView, url: String?) {
        if (url != null) {
            Glide.with(view.context)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
        }
    }
}