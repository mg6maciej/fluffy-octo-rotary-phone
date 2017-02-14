package pl.mg6.likeornot

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.*
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

    @Test
    fun shouldNotAllowToClickOtherItemsWhenDisplayed() {
        onLikableItem(R.id.likable_item_1).click()
        onLikableItem(R.id.likable_item_2).click()
        onId(R.id.grid_overlay_name).hasText("Michael Jackson")
    }

    @Test
    fun shouldNotBeDisplayedAtFirst() {
        onId(R.id.grid_overlay).isNotDisplayed()
    }

    @Test
    fun shouldHideAfterLiking() {
        onLikableItem(R.id.likable_item_1).click()
        onId(R.id.grid_overlay_like).click()
        onId(R.id.grid_overlay).isNotDisplayed()
    }
}
