package pl.mg6.likeornot

import io.reactivex.Single
import io.reactivex.Single.zip
import io.reactivex.functions.BiFunction

fun getLikables(api: LikableApi, callLocalLikes: () -> Single<LikableIdToStatus>): Single<List<Likable>>
        = zip(api.call(), callLocalLikes.invoke(), BiFunction(::toLikableList))

private fun toLikableList(list: List<LikableFromApi>, uuidToStatus: LikableIdToStatus) = list.map { it.toLikable(uuidToStatus) }

private fun LikableFromApi.toLikable(uuidToStatus: LikableIdToStatus) = Likable(uuid, name, image?.single(), uuidToStatus[uuid])
