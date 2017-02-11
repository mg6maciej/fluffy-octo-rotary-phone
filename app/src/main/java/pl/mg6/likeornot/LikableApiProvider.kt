package pl.mg6.likeornot

object LikableApiProvider {

    var override: LikableApi? = null

    fun get() = override!!
}
