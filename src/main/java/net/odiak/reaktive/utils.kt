package net.odiak.reaktive

val ErrorNotImplemented = { e: Throwable ->
    throw OnErrorNotImplementedException(e)
}
