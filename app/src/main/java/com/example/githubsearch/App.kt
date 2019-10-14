package com.example.githubsearch

import android.app.Application
import android.net.NetworkInfo
import com.example.githubsearch.bus.NetStatusBus
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.schedulers.Schedulers

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        initNetwork()
    }

    private fun initNetwork() {
        val disposable = ReactiveNetwork
            .observeNetworkConnectivity(this)
            .subscribeOn(Schedulers.io())
            .subscribe { connectivity ->
                if (connectivity.state() == NetworkInfo.State.CONNECTED) {
                    NetStatusBus.post(NetStatusBus.Status.Connected(connectivity.typeName()))
                } else {
                    NetStatusBus.post(NetStatusBus.Status.Disconnected(connectivity.typeName()))
                }
            }
    }
}