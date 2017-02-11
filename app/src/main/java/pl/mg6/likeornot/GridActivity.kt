package pl.mg6.likeornot

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.GridView
import android.widget.TextView

class GridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grid_activity)
        getLikables(LikableApiProvider.get()).subscribe { it: List<Likable> ->
            val pager = findViewById(R.id.grid_pager) as ViewPager
            val gridItems = it.mapIndexed { index, likable -> index to likable }.groupBy { it.first / 9 }.map { it.value.map { it.second } }
            pager.adapter = GridPagerAdapter(gridItems)
        }
    }
}

class GridPagerAdapter(private val gridItems: List<List<Likable>>) : PagerAdapter() {

    override fun getCount() = gridItems.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val it = gridItems[position]
        val grid = LayoutInflater.from(container.context).inflate(R.layout.likable_page, container, false) as GridLayout
        for (i in 0 until minOf(it.size, grid.childCount)) {
            val view = grid.getChildAt(i)
            val name = view.findViewById(R.id.likable_item_name) as TextView
            name.text = it[i].name
        }
        container.addView(grid)
        return grid
    }

    override fun isViewFromObject(view: View, obj: Any) = view === obj

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
