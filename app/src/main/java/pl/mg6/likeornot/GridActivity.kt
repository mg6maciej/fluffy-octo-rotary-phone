package pl.mg6.likeornot

import android.os.Bundle
import android.support.annotation.DimenRes
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Single.just

class GridActivity : AppCompatActivity() {

    private val overlayView by lazy { OverlayView(this, adapter::updateLikable) }
    private lateinit var adapter: GridPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grid_activity)
        getLikables(LikableApiProvider.get(), { just(emptyMap()) })
                .subscribe(this::showLikables, this::showError)
    }

    private fun showLikables(likables: List<Likable>) {
        val pager = findViewById(R.id.grid_pager) as ViewPager
        pager.pageMargin = calculatePageMargin()
        val gridItems = likables
                .mapIndexed { index, likable -> index to likable }
                .groupBy { it.first / 9 }
                .map { it.value.map { it.second } }
        adapter = GridPagerAdapter(gridItems, { overlayView.showLikable(it) })
        pager.adapter = adapter
    }

    private fun calculatePageMargin(): Int {
        return R.dimen.grid_pager_page_margin.pixelSize - 2 * R.dimen.grid_pager_padding.pixelSize
    }

    private val @receiver:DimenRes Int.pixelSize get() = resources.getDimensionPixelSize(this)

    private fun showError(error: Throwable) {
        Log.e("tag", "", error)
    }
}
