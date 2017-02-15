package pl.mg6.likeornot

import com.elpassion.android.commons.espresso.*
import org.junit.Rule
import org.junit.Test

class GridShowOverlayTest {

    @Rule @JvmField
    val rule = GridActivityTestRule()

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
        onLikableItem(R.id.likable_item_1).selectLike()
        onId(R.id.grid_overlay).isNotDisplayed()
    }

    @Test
    fun shouldHideAfterClickingAnyButton() {
        onLikableItem(R.id.likable_item_1).selectReallyLike()
        onLikableItem(R.id.likable_item_1).selectMeh()
        onLikableItem(R.id.likable_item_1).selectDontLike()
        onLikableItem(R.id.likable_item_1).selectHate()
        onId(R.id.grid_overlay).isNotDisplayed()
    }
}
