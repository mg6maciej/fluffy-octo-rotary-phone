package pl.mg6.likeornot

import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.action.ViewActions.swipeRight
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
                override fun call() = just((1..20).map { LikableFromApi("uuid-$it", "Name $it", listOf("https://d93golxnkabrk.cloudfront.net/things/bb339a05-a904-11e1-9412-005056900141.jpg?w=200")) })
            }
        }
    }

    @Test
    fun shouldShowNinthLikable() {
        onLikableItem(R.id.likable_item_9).hasName("Name 9")
    }

    @Test
    fun shouldShowTenthLikableAfterSwipe() {
        onId(R.id.grid_pager).perform(swipeLeft())
        onLikableItem(R.id.likable_item_1).hasName("Name 10")
    }

    @Test
    fun shouldBeAbleToSwipeTwoTimesAndBack() {
        onId(R.id.grid_pager).perform(swipeLeft())
        onId(R.id.grid_pager).perform(swipeLeft())
        onId(R.id.grid_pager).perform(swipeRight())
        onId(R.id.grid_pager).perform(swipeRight())
    }
}
