@file:JvmName("Main")
package net.odiak.reaktive

fun main(args: Array<String>) {
    val o = Observable<String> {
        it.onNext("hello")
        it.onNext("world")
    }
    o.subscribe {
        println("onNext: $it")
    }
}
