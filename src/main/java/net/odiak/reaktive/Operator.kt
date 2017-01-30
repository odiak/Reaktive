package net.odiak.reaktive

typealias Operator<R, T> = (Subscriber<R>) -> Subscriber<T>
