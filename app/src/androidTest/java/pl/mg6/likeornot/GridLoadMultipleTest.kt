package pl.mg6.likeornot

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import io.reactivex.Single.just
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.espresso.atPosition

class GridLoadMultipleTest {

    @Rule @JvmField
    val rule = object : ActivityTestRule<GridActivity>(GridActivity::class.java) {
        override fun beforeActivityLaunched() {
            LikableApiProvider.override = object : LikableApi {
                override fun call() = just(listOf(michaelJacksonLikableFromApi, wayneRooneyLikableFromApi))
            }
        }
    }

    @Test
    fun shouldShowLikables() {
        onView(allOf(withId(R.id.likable_item), atPosition(0))).check(ViewAssertions.matches(hasDescendant(allOf(withId(R.id.likable_item_name), withText("Michael Jackson")))))
        onView(allOf(withId(R.id.likable_item), atPosition(1))).check(ViewAssertions.matches(hasDescendant(allOf(withId(R.id.likable_item_name), withText("Wayne Rooney")))))
    }
}
