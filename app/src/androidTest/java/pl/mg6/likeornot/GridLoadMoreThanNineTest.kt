package pl.mg6.likeornot

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.onId
import io.reactivex.Single.just
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.espresso.atPosition

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
        onView(allOf(isDisplayed(), withId(R.id.likable_item), atPosition(8))).check(ViewAssertions.matches(hasDescendant(allOf(withId(R.id.likable_item_name), withText("Name 9")))))
    }

    @Test
    fun shouldShowNinthLikableAfterSwipe() {
        onId(R.id.grid_pager).perform(ViewActions.swipeLeft())
        onView(allOf(isDisplayed(), withId(R.id.likable_item), atPosition(0))).check(ViewAssertions.matches(hasDescendant(allOf(withId(R.id.likable_item_name), withText("Name 10")))))
    }
}
