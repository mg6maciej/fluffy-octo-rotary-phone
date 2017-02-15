package pl.mg6.likeornot

import org.junit.Rule
import org.junit.Test

class GridLoadMultipleTest {

    @Rule @JvmField
    val rule = GridActivityTestRule()

    @Test
    fun shouldShowLikables() {
        onLikableItem(R.id.likable_item_1).hasName("Michael Jackson")
        onLikableItem(R.id.likable_item_2).hasName("Wayne Rooney")
    }
}
