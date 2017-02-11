package pl.mg6.likeornot

import io.reactivex.Single

fun getLikables(api: LikableApi): Single<List<Likable>> = api.call().map(::toLikableList)

private fun toLikableList(list: List<LikableFromApi>) = list.map { it.toLikable() }

private fun LikableFromApi.toLikable() = Likable(uuid, name, image.single())
