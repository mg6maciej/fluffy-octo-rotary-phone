package pl.mg6.likeornot

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File

typealias LikableIdToStatus = Map<String, Status>

fun loadLocalLikes(context: Context): Single<LikableIdToStatus> {
    return Single.fromCallable { readLikesFile(context) }
            .map(::toUuidToStatusMap)
            .onErrorReturnItem(emptyMap())
}

private fun readLikesFile(context: Context) = likesFile(context).readLines()

private fun toUuidToStatusMap(lines: List<String>) = lines.associateBy(::extractUuid, ::extractStatus)

private fun extractUuid(line: String) = line.substringBefore(' ')

private fun extractStatus(line: String) = Status.valueOf(line.substringAfter(' '))

fun saveLocalLike(context: Context, likable: Likable, status: Status): Completable {
    return Completable.fromCallable { appendStatusToLikesFile(context, likable, status) }
}

private fun appendStatusToLikesFile(context: Context, likable: Likable, status: Status) {
    val line = "${likable.uuid} ${status}\n"
    likesFile(context).appendText(line)
}

private fun likesFile(context: Context) = File(context.filesDir, "likes")
