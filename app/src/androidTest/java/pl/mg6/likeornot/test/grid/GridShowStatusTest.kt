package pl.mg6.likeornot.test.grid

import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.R
import pl.mg6.likeornot.test.grid.interaction.onLikableItem
import pl.mg6.likeornot.test.grid.rule.GridActivityTestRule

class GridShowStatusTest {

    @Rule @JvmField
    val rule = GridActivityTestRule()

    @Test
    fun shouldShowLikeStatus() {
        onLikableItem(R.id.likable_item_1).selectLike()
        onLikableItem(R.id.likable_item_1).hasStatus(R.drawable.like)
    }

    @Test
    fun shouldShowHateStatus() {
        onLikableItem(R.id.likable_item_1).selectHate()
        onLikableItem(R.id.likable_item_1).hasStatus(R.drawable.hate)
    }

    @Test
    fun shouldNotShowStatusAtFirst() {
        onLikableItem(R.id.likable_item_1).doesNotHaveStatus()
    }

    @Test
    fun shouldShowStatusOverlay() {
        onLikableItem(R.id.likable_item_1).selectLike()
        onLikableItem(R.id.likable_item_1).hasStatusOverlay()
    }

    @Test
    fun shouldNotShowStatusOverlayAtFirst() {
        onLikableItem(R.id.likable_item_1).doesNotHaveStatusOverlay()
    }
}
