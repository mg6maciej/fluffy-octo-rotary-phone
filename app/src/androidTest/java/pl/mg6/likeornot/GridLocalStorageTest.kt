package pl.mg6.likeornot

import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.onId
import io.reactivex.Single.just
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.io.File

class GridLocalStorageTest {

    @Rule @JvmField
    val rule = object : ActivityTestRule<GridActivity>(GridActivity::class.java) {

        override fun beforeActivityLaunched() {
            LikableApiProvider.override = object : LikableApi {
                override fun call() = just(listOf(michaelJacksonLikableFromApi, wayneRooneyLikableFromApi))
            }
            likesFile.writeText(
                    "${michaelJacksonLikableFromApi.uuid} LIKE\n"
            )
        }

        override fun afterActivityFinished() {
            likesFile.delete()
        }
    }

    private val likesFile by lazy { File(getTargetContext().filesDir, "likes") }

    @Test
    fun shouldShowInitialLike() {
        onLikableItem(R.id.likable_item_1).hasStatus(R.drawable.like)
    }

    @Test
    fun shouldStoreLikableStatusLocally() {
        onLikableItem(R.id.likable_item_2).click()
        onId(R.id.grid_overlay_meh).click()
        assertEquals(
                "${michaelJacksonLikableFromApi.uuid} LIKE\n${wayneRooneyLikableFromApi.uuid} MEH\n",
                likesFile.readText())
    }
}
