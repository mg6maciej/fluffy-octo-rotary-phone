package pl.mg6.likeornot

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class GridPagerAdapter(private val gridItems: List<List<Likable>>) : PagerAdapter() {

    override fun getCount() = gridItems.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val it = gridItems[position]
        val grid = LayoutInflater.from(container.context).inflate(R.layout.likable_page, container, false) as ViewGroup
        for (i in 0 until minOf(it.size, grid.childCount)) {
            val view = grid.getChildAt(i)
            val name = view.findViewById(R.id.likable_item_name) as TextView
            name.text = it[i].name
            val image = view.findViewById(R.id.likable_item_image) as ImageView
            Glide.with(container.context).load(it[i].image).into(image)
        }
        for (i in it.size until grid.childCount) {
            grid.getChildAt(i).visibility = View.INVISIBLE
        }
        container.addView(grid)
        return grid
    }

    override fun isViewFromObject(view: View, obj: Any) = view === obj

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
