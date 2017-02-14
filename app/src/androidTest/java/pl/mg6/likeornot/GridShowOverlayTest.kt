package pl.mg6.likeornot

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.hasText
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import io.reactivex.Single.just
import org.junit.Rule
import org.junit.Test

class GridShowOverlayTest {

    @Rule @JvmField
    val rule = object : ActivityTestRule<GridActivity>(GridActivity::class.java) {
        override fun beforeActivityLaunched() {
            LikableApiProvider.override = object : LikableApi {
                override fun call() = just(listOf(michaelJacksonLikableFromApi, wayneRooneyLikableFromApi))
            }
        }
    }

    @Test
    fun shouldShowOverlay() {
        onLikableItem(R.id.likable_item_1).click()
        onId(R.id.grid_overlay).isDisplayed()
    }

    @Test
    fun shouldShowNameInOverlay() {
        onLikableItem(R.id.likable_item_1).click()
        onId(R.id.grid_overlay_name).hasText("Michael Jackson")
    }
}