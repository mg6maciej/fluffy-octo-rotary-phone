package pl.mg6.likeornot

import com.elpassion.android.commons.espresso.isNotDisplayed
import org.junit.Rule
import org.junit.Test

class GridLoadTest {

    @Rule @JvmField
    val rule = GridActivityTestRule(listOf(michaelJacksonLikableFromApi))

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
