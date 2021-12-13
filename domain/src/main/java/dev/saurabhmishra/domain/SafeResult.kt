package dev.saurabhmishra.domain

import java.lang.Exception

sealed class SafeResult<T> {
    class Success<T>(val data: T?): SafeResult<T>()
    class Failure<Nothing>(val exception: Exception, val msg: String = exception.message.orEmpty()): SafeResult<Nothing>()
}
