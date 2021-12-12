package dev.saurabhmishra.exoplayersample.utils

import java.util.concurrent.atomic.AtomicLong

object UniqueIdGenerator {
    private val id = AtomicLong(0L)

    fun generateId(): Long {
        return id.getAndIncrement()
    }
}