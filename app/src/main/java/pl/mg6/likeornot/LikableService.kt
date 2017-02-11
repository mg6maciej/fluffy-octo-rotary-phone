package pl.mg6.likeornot

import io.reactivex.Single

fun getLikables(): Single<List<Likable>> {
    return Single.just(emptyList())
}
