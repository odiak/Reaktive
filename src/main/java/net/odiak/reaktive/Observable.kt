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

    fun subscribe(onNext: (T) -> Unit): Subscription =
            subscribe(FunctionSubscriber(onNext, ErrorNotImplemented, {}))

    fun subscribe(onNext: (T) -> Unit,
                  onError: (Throwable) -> Unit): Subscription =
            subscribe(FunctionSubscriber(onNext, onError, {}))

    fun subscribe(onNext: (T) -> Unit,
                  onError: (Throwable) -> Unit,
                  onCompleted: () -> Unit): Subscription =
            subscribe(onNext, onError, onCompleted)

    fun <R> map(transformer: (T) -> R): Observable<R> =
            Observable(OnSubscribeMap(this, transformer))

    fun filter(predicator: (T) -> Boolean): Observable<T> =
            Observable(OnSubscribeFilter(this, predicator))
}
