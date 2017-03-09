package pl.mg6.likeornot.test.grid

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single.error
import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.R
import pl.mg6.likeornot.grid.api.impl.LikableApiProvider
import pl.mg6.likeornot.grid.view.impl.GridActivity
import pl.mg6.likeornot.infrastructure.logger.ErrorLogger
import pl.mg6.likeornot.infrastructure.logger.LogErrorFunc
import pl.mg6.likeornot.infrastructure.retrofit.RetrofitProvider
import pl.mg6.likeornot.test.grid.rule.GridActivityTestRule

class GridLoadErrorTest {

    private val logErrorMock: LogErrorFunc = mock()

    @Rule @JvmField
    val rule = object : ActivityTestRule<GridActivity>(GridActivity::class.java) {

        override fun beforeActivityLaunched() {
            ErrorLogger.override = logErrorMock
            RetrofitProvider.override = (GridActivityTestRule)::throwNoInternetInTests
            LikableApiProvider.override = mock {
                on { call() } doReturn error(Exception())
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
        verify(logErrorMock).invoke(eq("GridActivity"), eq("Cannot show likables!"), any())
    }

    @Test
    fun shouldShowLoadErrorMessage() {
        onId(R.id.grid_load_error).isDisplayed()
    }
}
