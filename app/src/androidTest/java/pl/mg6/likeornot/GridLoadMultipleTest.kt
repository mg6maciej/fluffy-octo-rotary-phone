package pl.mg6.likeornot

import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.rule.ActivityTestRule
import io.reactivex.Single.just
import org.junit.Rule
import org.junit.Test
import java.io.File

class GridLoadMultipleTest {

    @Rule @JvmField
    val rule = object : ActivityTestRule<GridActivity>(GridActivity::class.java) {

        private val likesFile by lazy { File(getTargetContext().filesDir, "likes") }

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

    @Test
    fun shouldShowLikables() {
        onLikableItem(R.id.likable_item_1).hasName("Michael Jackson")
        onLikableItem(R.id.likable_item_2).hasName("Wayne Rooney")
    }

    @Test
    fun shouldShowInitialLike() {
        onLikableItem(R.id.likable_item_1).hasStatus(R.drawable.like)
    }
}
