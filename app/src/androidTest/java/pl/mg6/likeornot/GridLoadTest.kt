package pl.mg6.likeornot

import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.helpers.hasName
import pl.mg6.likeornot.helpers.isNotDisplayed
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
}
