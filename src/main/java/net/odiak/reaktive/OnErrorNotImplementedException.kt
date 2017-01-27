package net.odiak.reaktive

class OnErrorNotImplementedException : RuntimeException {
    constructor(message: String?, e: Throwable) : super(message, e)
    constructor(e: Throwable) : super(e.message, e)
}
