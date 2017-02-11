package pl.mg6.likeornot

import io.reactivex.Single

fun getLikables(api: LikableApi): Single<List<Likable>> {
    return api.call().map { it.map { Likable(it.uuid, it.name, it.image.single()) } }
}
