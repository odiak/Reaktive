package net.odiak.reaktive

object Subscriptions {
    private val UNSUBSCRIBED = object : Subscription {
        override fun unsubscribe() {}
        override fun isUnsubscribed() = true
    }

    fun unsubscribed() = UNSUBSCRIBED
}
