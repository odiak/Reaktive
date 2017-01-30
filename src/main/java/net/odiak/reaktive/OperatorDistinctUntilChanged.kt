package net.odiak.reaktive

class OperatorDistinctUntilChanged<T> : Operator<T, T> {

    override fun invoke(child: Subscriber<T>): Subscriber<T> {
        return object : Subscriber<T>() {
            var previous: T? = null
            var hasPrevious = false

            override fun onNext(v: T) {
                val prev = previous
                previous = v
                if (hasPrevious) {
                    if (prev != v) {
                        child.onNext(v)
                    }
                } else {
                    hasPrevious = true
                    child.onNext(v)
                }
            }

            override fun onError(e: Throwable) {
                child.onError(e)
            }

            override fun onCompleted() {
                child.onCompleted()
            }
        }
    }
}
