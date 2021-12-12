package dev.saurabhmishra.exoplayersample.extensions

import dev.saurabhmishra.exoplayersample.BuildConfig
import dev.saurabhmishra.exoplayersample.utils.Wood
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

private val defaultCoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
    // On debug mode, we want the app to fail fast, so that we can see and correct the behavior
    // On other modes, failing is probably not an option
    if (BuildConfig.DEBUG) {
        throw exception
    } else {
        Wood.error( "Default Exception handler ${exception.localizedMessage}", exception)
    }
}

/**
 * @author Saurabh Mishra
 * Call this instead of launch, and all your exception will be logged via crashlytics if you don't
 * provide an exception handler
 * */
fun CoroutineScope.safeLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {

    val handler = context[CoroutineExceptionHandler.Key]

    val callingContext =
        handler?.let { context } ?: context + defaultCoroutineExceptionHandler

    return launch(callingContext, start, block = block)
}

