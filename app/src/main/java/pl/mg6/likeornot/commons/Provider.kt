package pl.mg6.likeornot.commons

import kotlin.LazyThreadSafetyMode.NONE

typealias Initializer<T> = () -> T

abstract class Provider<T>(initializer: Initializer<T>) {

    private val value by lazy(NONE, initializer)
    var override: Initializer<T>? = null

    fun get(): T {
        val local = override
        return if (local != null) local.invoke() else value
    }
}
