package pl.mg6.likeornot

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single.error
import org.junit.Rule
import org.junit.Test

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
