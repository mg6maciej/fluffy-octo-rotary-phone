package pl.mg6.likeornot

import com.elpassion.android.commons.espresso.isNotDisplayed
import com.elpassion.android.commons.espresso.onId
import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.helpers.onLikableItem

class GridLoadTest {

    @Rule @JvmField
    val rule = GridActivityTestRule(likablesFromApi = listOf(michaelJacksonLikableFromApi))

    @Test
    fun shouldShowLikable() {
        onLikableItem(R.id.likable_item_1).hasName("Michael Jackson")
    }

    @Test
    fun shouldHideUnusedLikableViews() {
        onLikableItem(R.id.likable_item_2).isNotDisplayed()
        onLikableItem(R.id.likable_item_9).isNotDisplayed()
    }

    @Test
    fun shouldNotShowLoadErrorMessage() {
        onId(R.id.grid_load_error).isNotDisplayed()
    }
}
