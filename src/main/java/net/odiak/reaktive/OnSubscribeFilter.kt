package net.odiak.reaktive

class OnSubscribeFilter<T>(
        private val source: Observable<T>,
        private val predicator: (T) -> Boolean) : OnSubscribe<T> {

    override fun invoke(s: Subscriber<T>) {
        val parent = FilterSubscriber(s, predicator)
        s.add(parent)
        source.subscribe(parent)
    }

    private class FilterSubscriber<T>(
            private val actual: Subscriber<T>,
            private val predicator: (T) -> Boolean) : Subscriber<T>() {

        private var done = false

        override fun onNext(v: T) {
            val result: Boolean
            try {
                result = predicator(v)
            } catch (e: Throwable) {
                unsubscribe()
                onError(e)
                return
            }
            if (result) {
                actual.onNext(v)
            }
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
