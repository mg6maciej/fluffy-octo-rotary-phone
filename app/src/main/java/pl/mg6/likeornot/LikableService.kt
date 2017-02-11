package pl.mg6.likeornot

fun getLikables(api: LikableApi) = api.call().map(::toLikableList)

private fun toLikableList(list: List<LikableFromApi>) = list.map { it.toLikable() }

private fun LikableFromApi.toLikable() = Likable(uuid, name, image.single())
