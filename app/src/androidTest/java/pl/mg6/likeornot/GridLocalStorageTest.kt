package pl.mg6.likeornot

import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GridLocalStorageTest {

    @Rule @JvmField
    val rule = GridActivityTestRule(likesFileContent = "${michaelJacksonLikableFromApi.uuid} LIKE\n")

    @Test
    fun shouldShowInitialLike() {
        onLikableItem(R.id.likable_item_1).hasStatus(R.drawable.like)
    }

    @Test
    fun shouldStoreLikableStatusLocally() {
        onLikableItem(R.id.likable_item_2).selectMeh()
        assertEquals(
                "${michaelJacksonLikableFromApi.uuid} LIKE\n${wayneRooneyLikableFromApi.uuid} MEH\n",
                rule.likesFile.readText())
    }

    @Test
    fun shouldShowInitialStatusOverlay() {
        onLikableItem(R.id.likable_item_1).hasStatusOverlay()
    }
}
