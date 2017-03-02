package pl.mg6.likeornot

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import io.reactivex.Single
import io.reactivex.Single.error
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class GridLoadErrorTest {

    private var called = false
    private val logErrorSpy: LogErrorFunc = { _, _, _ -> called = true }

    @Rule @JvmField
    val rule = object : ActivityTestRule<GridActivity>(GridActivity::class.java) {

        override fun beforeActivityLaunched() {
            ErrorLogger.override = logErrorSpy
            RetrofitProvider.override = (GridActivityTestRule)::throwNoInternetInTests
            LikableApiProvider.override = object : LikableApi {
                override fun call(): Single<List<LikableFromApi>> {
                    return error(Exception())
                }
            }
        }

        override fun afterActivityFinished() {
            ErrorLogger.override = null
            RetrofitProvider.override = null
            LikableApiProvider.override = null
        }
    }

    @Test
    fun shouldCallLogError() {
        Assert.assertTrue(called)
    }

    @Test
    fun shouldShowLoadErrorMessage() {
        onId(R.id.grid_load_error).isDisplayed()
    }
}
