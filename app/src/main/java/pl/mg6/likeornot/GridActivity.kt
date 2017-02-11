package pl.mg6.likeornot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.TextView

class GridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grid_activity)
        getLikables(LikableApiProvider.get()).subscribe { it: List<Likable> ->
            val grid = findViewById(R.id.grid_layout) as ViewGroup
            for (i in 0 until minOf(it.size, grid.childCount)) {
                val view = grid.getChildAt(i)
                val name = view.findViewById(R.id.likable_item_name) as TextView
                name.text = it[i].name
            }
        }
    }
}
