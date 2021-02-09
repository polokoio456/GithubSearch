package com.example.githubsearch

import android.app.Application
import com.example.githubsearch.module.adapterModule
import com.example.githubsearch.module.remoteModule
import com.example.githubsearch.module.repositoryModule
import com.example.githubsearch.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    adapterModule,
                    remoteModule
                )
            )
        }
    }
}