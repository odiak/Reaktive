package net.odiak.reaktive

class FunctionSubscriber<T>(
        private val onNext: (T) -> Unit,
        private val onError: (Throwable) -> Unit,
        private val onCompleted: () -> Unit) :
        Subscriber<T>() {

    override fun onNext(v: T) {
        onNext.invoke(v)
    }

    override fun onError(e: Throwable) {
        onError.invoke(e)
    }

    override fun onCompleted() {
        onCompleted.invoke()
    }
}
