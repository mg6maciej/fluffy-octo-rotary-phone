package pl.mg6.likeornot

import android.content.Context
import io.reactivex.Single
import java.io.File

fun callLocalLikes(context: Context): Single<Map<String, Status>> {
    val likesFile = File(context.filesDir, "likes")
    return if (likesFile.exists()) {
        Single.fromCallable { likesFile.readLines() }
                .map(::toUuidToStatusMap)
    } else {
        Single.just(emptyMap())
    }
}

private fun toUuidToStatusMap(lines: List<String>) = lines.associateBy(::extractUuid, ::extractStatus)

private fun extractUuid(line: String) = line.substringBefore(' ')

private fun extractStatus(line: String) = Status.valueOf(line.substringAfter(' '))
