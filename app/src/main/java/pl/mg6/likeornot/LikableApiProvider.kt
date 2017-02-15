package pl.mg6.likeornot

object LikableApiProvider {

    private val api by lazy { RetrofitProvider.get().create(LikableApi::class.java) }
    var override: LikableApi? = null

    fun get() = override ?: api
}
