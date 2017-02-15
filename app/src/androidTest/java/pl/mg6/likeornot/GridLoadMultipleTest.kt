package pl.mg6.likeornot

import android.support.test.rule.ActivityTestRule
import io.reactivex.Single.just
import org.junit.Rule
import org.junit.Test

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
        onLikableItem(R.id.likable_item_1).hasName("Michael Jackson")
        onLikableItem(R.id.likable_item_2).hasName("Wayne Rooney")
    }
}
