package pl.mg6.likeornot.grid.view.impl

import android.support.annotation.DrawableRes
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import pl.mg6.likeornot.R
import pl.mg6.likeornot.grid.entity.Likable
import pl.mg6.likeornot.grid.entity.Status
import pl.mg6.likeornot.infrastructure.image_loader.ImageLoader

class GridPagerAdapter(private val gridItems: List<List<Likable>>, private val clickCallback: (Likable) -> Unit) : PagerAdapter() {

    private val likableToView = mutableMapOf<Likable, View>()

    override fun getCount() = gridItems.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val likables = gridItems[position]
        val grid = LayoutInflater.from(container.context).inflate(R.layout.likable_page, container, false) as ViewGroup
        for (i in 0 until minOf(likables.size, grid.childCount)) {
            val likable = likables[i]
            val view = grid.getChildAt(i)
            view.setOnClickListener {
                clickCallback.invoke(likable)
            }
            val name = view.findViewById(R.id.likable_item_name) as TextView
            name.text = likable.name
            val image = view.findViewById(R.id.likable_item_image) as ImageView
            ImageLoader.loadFromUrl(image, likable.image)
            likable.status?.let {
                val status = view.findViewById(R.id.likable_item_status) as ImageView
                status.setImageResource(it.imageId)
                view.findViewById(R.id.likable_item_status_overlay).visibility = VISIBLE
            }
            likableToView[likable] = view
            view.tag = likable
        }
        for (i in likables.size until grid.childCount) {
            grid.getChildAt(i).visibility = View.INVISIBLE
        }
        container.addView(grid)
        return grid
    }

    override fun isViewFromObject(view: View, obj: Any) = view === obj

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        val grid = obj as ViewGroup
        for (i in 0 until grid.childCount) {
            val likable = grid.getChildAt(i).tag as Likable?
            if (likable != null) {
                likableToView.remove(likable)
            }
        }
        container.removeView(grid)
    }

    fun updateLikable(likable: Likable, @DrawableRes imageId: Int) {
        val view = likableToView.remove(likable)!!
        likable.status = Status.values().single { it.imageId == imageId }
        likableToView[likable] = view
        val status = view.findViewById(R.id.likable_item_status) as ImageView
        status.setImageResource(imageId)
        view.findViewById(R.id.likable_item_status_overlay).visibility = VISIBLE
    }
}
