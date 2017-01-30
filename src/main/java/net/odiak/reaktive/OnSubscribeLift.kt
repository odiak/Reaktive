package net.odiak.reaktive

class OnSubscribeLift<T, R>(
        private val parent: OnSubscribe<T>,
        private val operator_: Operator<R, T>): OnSubscribe<R> {

    override fun invoke(subscriber: Subscriber<R>) {
        try {
            val st = operator_(subscriber)
            try {
                st.onStart()
                parent(st)
            } catch (e: Throwable) {
                st.onError(e)
            }
        } catch (e: Throwable) {
            subscriber.onError(e)
        }
    }
}
