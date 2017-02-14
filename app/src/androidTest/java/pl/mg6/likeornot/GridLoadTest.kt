package pl.mg6.likeornot

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.onId
import io.reactivex.Single.just
import org.junit.Rule
import org.junit.Test

class GridLoadTest {

    @Rule @JvmField
    val rule = object : ActivityTestRule<GridActivity>(GridActivity::class.java) {
        override fun beforeActivityLaunched() {
            LikableApiProvider.override = object : LikableApi {
                override fun call() = just(listOf(michaelJacksonLikableFromApi))
            }
        }
    }

    @Test
    fun shouldShowLikable() {
        onLikableItem(R.id.likable_item_1).hasName("Michael Jackson")
    }
}
