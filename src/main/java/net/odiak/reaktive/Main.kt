@file:JvmName("Main")
package net.odiak.reaktive

fun main(args: Array<String>) {
    val o = Observable<String> {
        it.onNext("hello")
        it.onNext("world")
    }
    o.subscribe(object : Subscriber<String>() {
        override fun onNext(v: String) {
            println("onNext: $v")
        }

        override fun onError(e: Throwable) {
        }

        override fun onCompleted() {
        }
    })
}
