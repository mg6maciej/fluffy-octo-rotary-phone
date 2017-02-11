package pl.mg6.likeornot

import io.reactivex.Single

interface LikableApi {

    fun call(): Single<List<LikableFromApi>>
}