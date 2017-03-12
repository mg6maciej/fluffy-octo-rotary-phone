package pl.mg6.likeornot.test.grid

import android.support.test.espresso.Espresso
import android.support.test.rule.ActivityTestRule
import android.util.Log
import com.elpassion.android.commons.espresso.onId
import com.elpassion.android.commons.espresso.swipeLeft
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single.just
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.R
import pl.mg6.likeornot.commons.ViewPagerIdlingResource
import pl.mg6.likeornot.commons.throwNoInternetInTests
import pl.mg6.likeornot.commons.view.LoadFromUrlFunc
import pl.mg6.likeornot.commons.view.loadFromUrlOverride
import pl.mg6.likeornot.grid.api.LikableFromApi
import pl.mg6.likeornot.grid.api.impl.LikableApiProvider
import pl.mg6.likeornot.grid.view.impl.GridActivity
import pl.mg6.likeornot.infrastructure.logger.ErrorLogger
import pl.mg6.likeornot.infrastructure.retrofit.RetrofitProvider
import pl.mg6.likeornot.test.grid.interaction.onLikableItem

class GridLoadFromUrlTest {

    private val loadFromUrlMock: LoadFromUrlFunc = mock()

    @Rule @JvmField
    val rule = object : ActivityTestRule<GridActivity>(GridActivity::class.java) {

        override fun beforeActivityLaunched() {
            loadFromUrlOverride = loadFromUrlMock
            ErrorLogger.override = { tag, message, error -> Log.w(tag, message, error) }
            RetrofitProvider.override = ::throwNoInternetInTests
            LikableApiProvider.override = {
                mock { on { call() } doReturn just(likablesFromApi) }
            }
        }

        override fun afterActivityFinished() {
            loadFromUrlOverride = null
            ErrorLogger.override = null
            RetrofitProvider.override = null
            LikableApiProvider.override = null
        }
    }

    private val pagerIdlingResource by lazy { ViewPagerIdlingResource(rule, R.id.grid_pager) }

    @Before
    fun registerViewPagerIdlingResource() {
        Espresso.registerIdlingResources(pagerIdlingResource)
    }

    @After
    fun unregisterViewPagerIdlingResource() {
        Espresso.unregisterIdlingResources(pagerIdlingResource)
    }

    @Test
    fun shouldLoadImagesFromFirstAndSecondPage() {
        repeat(18) {
            verify(loadFromUrlMock, times(1)).invoke(any(), eq("http://image.png_$it"))
        }
        verifyNoMoreInteractions(loadFromUrlMock)
    }

    @Test
    fun shouldLoadImagesFromThirdPageAfterSwipe() {
        onId(R.id.grid_pager).swipeLeft()
        repeat(27) {
            verify(loadFromUrlMock, times(1)).invoke(any(), eq("http://image.png_$it"))
        }
        verifyNoMoreInteractions(loadFromUrlMock)
    }

    @Test
    fun shouldLoadClickedImageTwice() {
        onLikableItem(R.id.likable_item_1).click()
        verify(loadFromUrlMock, times(2)).invoke(any(), eq("http://image.png_0"))
    }

    companion object {

        private val likablesFromApi by lazy { (0..30).map { LikableFromApi("uuid-$it", "Name $it", listOf("http://image.png_$it")) } }
    }
}
