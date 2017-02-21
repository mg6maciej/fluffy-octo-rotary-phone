package pl.mg6.likeornot

import android.support.test.rule.ActivityTestRule
import com.elpassion.android.commons.espresso.isDisplayed
import com.elpassion.android.commons.espresso.onId
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class GridLoadingStateTest {

    @Rule @JvmField
    val rule = object : ActivityTestRule<GridActivity>(GridActivity::class.java) {
        override fun beforeActivityLaunched() {
            RetrofitProvider.override = (GridActivityTestRule)::throwNoInternetInTests
            LikableApiProvider.override = object : LikableApi {
                override fun call(): Single<List<LikableFromApi>> {
                    return Single.never()
                }
            }
        }
    }

    @Test
    fun shouldShowLoader() {
        onId(R.id.loader).isDisplayed()
    }
}
