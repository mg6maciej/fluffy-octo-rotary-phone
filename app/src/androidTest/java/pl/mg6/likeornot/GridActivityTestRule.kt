package pl.mg6.likeornot

import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.rule.ActivityTestRule
import android.util.Log
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single.just
import java.io.File

class GridActivityTestRule(
        private val likablesFromApi: List<LikableFromApi> = listOf(michaelJacksonLikableFromApi, wayneRooneyLikableFromApi),
        private val likesFileContent: String? = null)
    : ActivityTestRule<GridActivity>(GridActivity::class.java) {

    val likesFile by lazy { File(getTargetContext().filesDir, "likes") }

    override fun beforeActivityLaunched() {
        ErrorLogger.override = { tag, message, error -> Log.w(tag, message, error) }
        RetrofitProvider.override = (GridActivityTestRule)::throwNoInternetInTests
        LikableApiProvider.override = mock {
            on { call() } doReturn just(likablesFromApi)
        }
        if (likesFileContent != null) {
            likesFile.writeText(likesFileContent)
        }
    }

    override fun afterActivityFinished() {
        ErrorLogger.override = null
        RetrofitProvider.override = null
        LikableApiProvider.override = null
        likesFile.delete()
    }

    companion object {

        fun throwNoInternetInTests(): Nothing = throw Exception("No internet in tests!")
    }
}
