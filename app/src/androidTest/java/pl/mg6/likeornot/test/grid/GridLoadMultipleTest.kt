package pl.mg6.likeornot.test.grid

import org.junit.Rule
import org.junit.Test
import pl.mg6.likeornot.R
import pl.mg6.likeornot.test.grid.interaction.onLikableItem
import pl.mg6.likeornot.test.grid.rule.GridActivityTestRule

class GridLoadMultipleTest {

    @Rule @JvmField
    val rule = GridActivityTestRule()

    @Test
    fun shouldShowLikables() {
        onLikableItem(R.id.likable_item_1).hasName("Michael Jackson")
        onLikableItem(R.id.likable_item_2).hasName("Wayne Rooney")
    }
}
