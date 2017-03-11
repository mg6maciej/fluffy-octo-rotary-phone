package pl.mg6.likeornot.grid.api.impl

import pl.mg6.likeornot.grid.api.LikableApi
import pl.mg6.likeornot.infrastructure.retrofit.ApiProvider

object LikableApiProvider : ApiProvider<LikableApi>(LikableApi::class.java)
