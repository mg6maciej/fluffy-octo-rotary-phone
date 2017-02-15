package pl.mg6.likeornot

import io.reactivex.Single
import retrofit2.http.GET

interface LikableApi {

    @GET("api/likeable.json")
    fun call(): Single<List<LikableFromApi>>
}
