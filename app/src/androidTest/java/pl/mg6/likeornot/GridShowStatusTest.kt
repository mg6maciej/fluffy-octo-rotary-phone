package pl.mg6.likeornot

import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.onId
import org.junit.Rule
import org.junit.Test

class GridShowStatusTest {

    @Rule @JvmField
    val rule = GridActivityTestRule()

    @Test
    fun shouldShowLikeStatus() {
        onLikableItem(R.id.likable_item_1).click()
        onId(R.id.grid_overlay_like).click()
        onLikableItem(R.id.likable_item_1).hasStatus(R.drawable.like)
    }

    @Test
    fun shouldShowHateStatus() {
        onLikableItem(R.id.likable_item_1).click()
        onId(R.id.grid_overlay_hate).click()
        onLikableItem(R.id.likable_item_1).hasStatus(R.drawable.hate)
    }

    @Test
    fun shouldNotShowStatusAtFirst() {
        onLikableItem(R.id.likable_item_1).hasNoStatus()
    }

    @Test
    fun shouldShowStatusOverlay() {
        onLikableItem(R.id.likable_item_1).click()
        onId(R.id.grid_overlay_like).click()
        onLikableItem(R.id.likable_item_1).hasStatusOverlay()
    }

    @Test
    fun shouldNotShowStatusOverlayAtFirst() {
        onLikableItem(R.id.likable_item_1).doesntHaveStatusOverlay()
    }
}
