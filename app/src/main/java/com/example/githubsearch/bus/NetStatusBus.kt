package com.example.githubsearch.bus

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay

object NetStatusBus {
    private var relay: Relay<Status> = BehaviorRelay.create<Status>().toSerialized()

    fun post(status: Status) {
        relay.accept(status)
    }

    fun relay(): Relay<Status> {
        return relay
    }

    sealed class Status {
        data class Connected(val type: String) : Status()
        data class Disconnected(val type: String) : Status()
    }
}