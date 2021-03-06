package pl.mg6.likeornot.test.grid

import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single.error
import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.R
import pl.mg6.likeornot.commons.activityTestRule
import pl.mg6.likeornot.commons.throwNoInternetInTests
import pl.mg6.likeornot.grid.api.impl.LikableApiProvider
import pl.mg6.likeornot.grid.view.impl.GridActivity
import pl.mg6.likeornot.infrastructure.logger.ErrorLogger
import pl.mg6.likeornot.infrastructure.logger.LogErrorFunc
import pl.mg6.likeornot.infrastructure.retrofit.RetrofitProvider

class GridLoadErrorTest {

    private val logErrorMock: LogErrorFunc = mock()

    @Rule @JvmField
    val rule = activityTestRule<GridActivity>(before = {
        ErrorLogger.override = logErrorMock
        RetrofitProvider.override = ::throwNoInternetInTests
        LikableApiProvider.override = {
            mock { on { call() } doReturn error(Exception()) }
        }
    }, after = {
        ErrorLogger.override = null
        RetrofitProvider.override = null
        LikableApiProvider.override = null
    })

    @Test
    fun shouldCallLogError() {
        verify(logErrorMock).invoke(eq("GridActivity"), eq("Cannot show likables!"), any())
    }

    @Test
    fun shouldShowLoadErrorMessage() {
        onId(R.id.grid_load_error).isDisplayed()
    }
}
