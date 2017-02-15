package pl.mg6.likeornot

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File

typealias LikableToStatus = Map<String, Status>

fun callLocalLikes(context: Context): Single<LikableToStatus> {
    return Single.fromCallable { readLikesFile(context) }
            .map(::toUuidToStatusMap)
            .onErrorReturnItem(emptyMap())
}

private fun readLikesFile(context: Context) = File(context.filesDir, "likes").readLines()

private fun toUuidToStatusMap(lines: List<String>) = lines.associateBy(::extractUuid, ::extractStatus)

private fun extractUuid(line: String) = line.substringBefore(' ')

private fun extractStatus(line: String) = Status.valueOf(line.substringAfter(' '))

fun addLocalLike(context: Context, likable: Likable, status: Status): Completable {
    return Completable.fromCallable { File(context.filesDir, "likes").appendText("${likable.uuid} ${status}\n") }
}
