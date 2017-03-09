package pl.mg6.likeornot.test.grid

import com.elpassion.android.commons.espresso.hasText
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.isNotDisplayed
import com.elpassion.android.commons.espresso.onId
import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.R
import pl.mg6.likeornot.test.grid.interaction.onLikableItem
import pl.mg6.likeornot.test.grid.rule.GridActivityTestRule

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
