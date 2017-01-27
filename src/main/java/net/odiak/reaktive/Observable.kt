package net.odiak.reaktive

open class Observable<T>(private val onSubscribe: OnSubscribe<T>) {

    fun subscribe(subscriber: Subscriber<T>): Subscription {
        subscriber.onStart()
        try {
            onSubscribe(subscriber)
            return subscriber
        } catch (e: Throwable) {
            if (!subscriber.isUnsubscribed()) {
                try {
                    subscriber.onError(e)
                } catch (e2: Throwable) {
                    throw OnErrorFailedException(e2)
                }
            }
            return Subscriptions.unsubscribed()
        }
    }
}
