package pl.mg6.likeornot.commons

import kotlin.LazyThreadSafetyMode.NONE

abstract class Provider<T>(initializer: () -> T) {

    private val value by lazy(NONE, initializer)
    var override: T? = null

    fun get(): T = override ?: value
}
