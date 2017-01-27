package net.odiak.reaktive

class OnErrorFailedException : RuntimeException {
    constructor(message: String?, e: Throwable) : super(message, e)
    constructor(e: Throwable) : super(e.message, e)
}
