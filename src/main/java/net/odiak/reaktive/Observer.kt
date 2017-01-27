package net.odiak.reaktive

interface Observer<T> {
    fun onNext(v: T)
    fun onError(e: Throwable)
    fun onCompleted()
}

