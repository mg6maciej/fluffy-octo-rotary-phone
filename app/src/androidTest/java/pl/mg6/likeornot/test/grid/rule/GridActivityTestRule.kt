package pl.mg6.likeornot.test.grid.rule

import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.rule.ActivityTestRule
import android.util.Log
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single.just
import pl.mg6.likeornot.commons.throwNoInternetInTests
import pl.mg6.likeornot.grid.api.LikableFromApi
import pl.mg6.likeornot.grid.api.impl.LikableApiProvider
import pl.mg6.likeornot.grid.api.michaelJacksonLikableFromApi
import pl.mg6.likeornot.grid.api.wayneRooneyLikableFromApi
import pl.mg6.likeornot.grid.view.impl.GridActivity
import pl.mg6.likeornot.infrastructure.logger.ErrorLogger
import pl.mg6.likeornot.infrastructure.retrofit.RetrofitProvider
import java.io.File

class GridActivityTestRule(
        private val likablesFromApi: List<LikableFromApi> = listOf(michaelJacksonLikableFromApi, wayneRooneyLikableFromApi),
        private val likesFileContent: String? = null)
    : ActivityTestRule<GridActivity>(GridActivity::class.java) {

    val likesFile by lazy { File(getTargetContext().filesDir, "likes") }

    override fun beforeActivityLaunched() {
        ErrorLogger.override = { tag, message, error -> Log.w(tag, message, error) }
        RetrofitProvider.override = ::throwNoInternetInTests
        LikableApiProvider.override = {
            mock { on { call() } doReturn just(likablesFromApi) }
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
}
