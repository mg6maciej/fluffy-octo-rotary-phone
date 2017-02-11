package pl.mg6.likeornot

import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.onId
import io.reactivex.Single.just
import org.junit.Rule
import org.junit.Test

class GridLoadMoreThanNineTest {

    @Rule @JvmField
    val rule = object : ActivityTestRule<GridActivity>(GridActivity::class.java) {
        override fun beforeActivityLaunched() {
            LikableApiProvider.override = object : LikableApi {
                override fun call() = just((1..10).map { LikableFromApi("uuid-$it", "Name $it", listOf("")) })
            }
        }
    }

    @Test
    fun shouldShowEighthLikable() {
        onLikableItemAtPosition(8).hasName("Name 9")
    }

    @Test
    fun shouldShowNinthLikableAfterSwipe() {
        onId(R.id.grid_pager).perform(swipeLeft())
        onLikableItemAtPosition(0).hasName("Name 10")
    }
}
