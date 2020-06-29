package com.example.githubsearch.helper

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitGenerator {
    companion object {
        private const val TIMEOUT_SEC = 5L
        private const val DOMAIN_BASE_URL = "https://api.github.com"

        @Volatile
        private var INSTANCE: Retrofit? = null

        fun getRetrofit(): Retrofit =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildRetrofit().also { INSTANCE = it }
            }

        private fun buildRetrofit() =
            Retrofit.Builder()
                .baseUrl(DOMAIN_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()

        private fun getOkHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

            return builder.build()
        }
    }
}