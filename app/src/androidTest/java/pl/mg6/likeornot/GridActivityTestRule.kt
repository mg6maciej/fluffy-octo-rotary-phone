package pl.mg6.likeornot

import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.rule.ActivityTestRule
import io.reactivex.Single
import io.reactivex.Single.just
import java.io.File

class GridActivityTestRule(
        private val likablesFromApi: List<LikableFromApi> = listOf(michaelJacksonLikableFromApi, wayneRooneyLikableFromApi),
        private val likesFileContent: String? = null)
    : ActivityTestRule<GridActivity>(GridActivity::class.java) {

    val likesFile by lazy { File(getTargetContext().filesDir, "likes") }

    override fun beforeActivityLaunched() {
        LikableApiProvider.override = object : LikableApi {
            override fun call(): Single<List<LikableFromApi>> {
                return just(likablesFromApi)
            }
        }
        if (likesFileContent != null) {
            likesFile.writeText(likesFileContent)
        }
    }

    override fun afterActivityFinished() {
        LikableApiProvider.override = null
        likesFile.delete()
    }
}
