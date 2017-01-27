package net.odiak.reaktive

interface Subscription {
    fun unsubscribe()
    fun isUnsubscribed(): Boolean
}
