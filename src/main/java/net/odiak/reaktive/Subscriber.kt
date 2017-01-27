package net.odiak.reaktive

abstract class Subscriber<T>() : Observer<T>, Subscription {

    private val subscriptions = mutableListOf<Subscription>()
    private var unsubscribed = false

    fun add(subscription: Subscription) {
        subscriptions.add(subscription)
    }

    override fun unsubscribe() {
        subscriptions.forEach { it.unsubscribe() }
        unsubscribed = true
    }

    override fun isUnsubscribed() = unsubscribed

    open fun onStart() {}
}
