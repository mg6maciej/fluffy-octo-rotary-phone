package pl.mg6.likeornot

import com.elpassion.android.commons.espresso.onId
import com.elpassion.android.commons.espresso.swipeLeft
import com.elpassion.android.commons.espresso.swipeRight
import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.helpers.onLikableItem

class GridLoadMoreThanNineTest {

    @Rule @JvmField
    val rule = GridActivityTestRule(likablesFromApi = likablesFromApi)

    @Test
    fun shouldShowFirstAndNinthLikable() {
        onLikableItem(R.id.likable_item_1).hasName("Name 1")
        onLikableItem(R.id.likable_item_9).hasName("Name 9")
    }

    @Test
    fun shouldShowTenthLikableAfterSwipe() {
        onId(R.id.grid_pager).swipeLeft()
        onLikableItem(R.id.likable_item_1).hasName("Name 10")
    }

    @Test
    fun shouldShowStatusAndOverlayAfterComingBack() {
        onLikableItem(R.id.likable_item_1).selectDontLike()
        onId(R.id.grid_pager)
                .swipeLeft().swipeLeft()
                .swipeRight().swipeRight()
        onLikableItem(R.id.likable_item_1)
                .hasStatus(R.drawable.dont_like)
                .hasStatusOverlay()
    }

    companion object {

        private val likablesFromApi by lazy { (1..20).map { LikableFromApi("uuid-$it", "Name $it", null) } }
    }
}
