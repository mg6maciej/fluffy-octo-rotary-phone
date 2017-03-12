package pl.mg6.likeornot.test.grid

import android.support.test.espresso.Espresso
import com.elpassion.android.commons.espresso.onId
import com.elpassion.android.commons.espresso.swipeLeft
import com.elpassion.android.commons.espresso.swipeRight
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.R
import pl.mg6.likeornot.commons.ViewPagerIdlingResource
import pl.mg6.likeornot.grid.api.LikableFromApi
import pl.mg6.likeornot.test.grid.interaction.onLikableItem
import pl.mg6.likeornot.test.grid.rule.GridActivityTestRule

class GridLoadMoreThanNineTest {

    @Rule @JvmField
    val rule = GridActivityTestRule(likablesFromApi = likablesFromApi)

    private val pagerIdlingResource by lazy { ViewPagerIdlingResource(rule, R.id.grid_pager) }

    @Before
    fun registerViewPagerIdlingResource() {
        Espresso.registerIdlingResources(pagerIdlingResource)
    }

    @After
    fun unregisterViewPagerIdlingResource() {
        Espresso.unregisterIdlingResources(pagerIdlingResource)
    }

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
