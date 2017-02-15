package pl.mg6.likeornot

data class Likable(
        val uuid: String,
        val name: String,
        val image: String?,
        var status: Status?)
