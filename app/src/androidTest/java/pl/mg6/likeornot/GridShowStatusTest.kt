package pl.mg6.likeornot

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.onId
import io.reactivex.Single.just
import org.junit.Rule
import org.junit.Test
import java.io.File

class GridShowStatusTest {

    @Rule @JvmField
    val rule = object : ActivityTestRule<GridActivity>(GridActivity::class.java) {

        override fun beforeActivityLaunched() {
            LikableApiProvider.override = object : LikableApi {
                override fun call() = just(listOf(michaelJacksonLikableFromApi, wayneRooneyLikableFromApi))
            }
        }

        override fun afterActivityFinished() {
            likesFile.delete()
        }
    }

    private val likesFile by lazy { File(InstrumentationRegistry.getTargetContext().filesDir, "likes") }

    @Test
    fun shouldShowLikeStatus() {
        onLikableItem(R.id.likable_item_1).click()
        onId(R.id.grid_overlay_like).click()
        onLikableItem(R.id.likable_item_1).hasStatus(R.drawable.like)
    }

    @Test
    fun shouldShowHateStatus() {
        onLikableItem(R.id.likable_item_1).click()
        onId(R.id.grid_overlay_hate).click()
        onLikableItem(R.id.likable_item_1).hasStatus(R.drawable.hate)
    }

    @Test
    fun shouldNotShowStatusAtFirst() {
        onLikableItem(R.id.likable_item_1).hasNoStatus()
    }
}