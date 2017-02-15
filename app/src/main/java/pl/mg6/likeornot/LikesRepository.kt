package pl.mg6.likeornot

import android.content.Context
import io.reactivex.Single
import java.io.File

fun callLocalLikes(context: Context): Single<Map<String, Status>> {
    val likesFile = File(context.filesDir, "likes")
    return if (!likesFile.exists()) {
        Single.just(emptyMap())
    } else {
        Single.fromCallable { likesFile.readLines() }
                .map { it.associateBy({ it.substringBefore(' ') }, { Status.valueOf(it.substringAfter(' ')) }) }
    }
}
