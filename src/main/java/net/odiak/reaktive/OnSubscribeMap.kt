package net.odiak.reaktive

class OnSubscribeMap<T, R>(
        private val source: Observable<T>,
        private val transform: (T) -> R) : OnSubscribe<R> {

    override fun invoke(s: Subscriber<R>) {
        val parent = MapSubscriber(s, transform)
        s.add(parent)
        source.subscribe(parent)
    }

    private class MapSubscriber<T, R>(
            private val actual: Subscriber<R>,
            private val transform: (T) -> R) : Subscriber<T>() {

        private var done = false

        override fun onNext(v: T) {
            val result: R
            try {
                result = transform(v)
            } catch (e: Throwable) {
                unsubscribe()
                onError(e)
                return
            }
            actual.onNext(result)
        }

        override fun onError(e: Throwable) {
            if (done) return

            done = true
            actual.onError(e)
        }

        override fun onCompleted() {
            if (done) return

            actual.onCompleted()
        }
    }
}
