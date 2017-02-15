package pl.mg6.likeornot

import io.reactivex.Single
import io.reactivex.Single.zip
import io.reactivex.functions.BiFunction

fun getLikables(api: LikableApi, localLikes: () -> Single<Map<String, Status>>): Single<List<Likable>>
        = zip(api.call(), localLikes.invoke(), BiFunction(::toLikableList))

private fun toLikableList(list: List<LikableFromApi>, uuidToStatus: Map<String, Status>) = list.map { it.toLikable(uuidToStatus) }

private fun LikableFromApi.toLikable(uuidToStatus: Map<String, Status>) = Likable(uuid, name, image.single(), uuidToStatus[uuid])
