package pl.mg6.likeornot.test.grid

import com.elpassion.android.commons.espresso.isNotDisplayed
import com.elpassion.android.commons.espresso.onId
import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.R
import pl.mg6.likeornot.grid.api.michaelJacksonLikableFromApi
import pl.mg6.likeornot.test.grid.interaction.onLikableItem
import pl.mg6.likeornot.test.grid.rule.GridActivityTestRule

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
