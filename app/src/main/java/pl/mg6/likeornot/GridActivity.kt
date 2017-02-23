package pl.mg6.likeornot

import android.os.Bundle
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pl.mg6.likeornot.commons.batch

class GridActivity : AppCompatActivity() {

    private val overlayView by lazy { OverlayView(this, this::updateLikable) }
    private lateinit var adapter: GridPagerAdapter
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grid_activity)
        disposable = getLikables(LikableApiProvider.get(), { loadLocalLikes(this).subscribeOn(Schedulers.io()) })
                .map { it.batch(9) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showLikables, this::showError)
    }

    private fun showLikables(gridItems: List<List<Likable>>) {
        val pager = findViewById(R.id.grid_pager) as ViewPager
        pager.pageMargin = calculatePageMargin()
        adapter = GridPagerAdapter(gridItems, overlayView::showLikable)
        pager.adapter = adapter
    }

    private fun updateLikable(likable: Likable, @DrawableRes imageId: Int) {
        adapter.updateLikable(likable, imageId)
        saveLocalLike(this, likable, Status.values().single { it.imageId == imageId })
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    private fun calculatePageMargin(): Int {
        return R.dimen.grid_pager_page_margin.pixelSize - 2 * R.dimen.grid_pager_padding.pixelSize
    }

    private val @receiver:DimenRes Int.pixelSize get() = resources.getDimensionPixelSize(this)

    private fun showError(error: Throwable) {
        Log.e("tag", "", error)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
