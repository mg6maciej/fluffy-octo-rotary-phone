package pl.mg6.likeornot.grid.api.impl

import pl.mg6.likeornot.grid.api.LikableApi
import pl.mg6.likeornot.infrastructure.retrofit.RetrofitProvider

object LikableApiProvider {

    private val api by lazy { RetrofitProvider.get().create(LikableApi::class.java) }
    var override: LikableApi? = null

    fun get() = override ?: api
}
