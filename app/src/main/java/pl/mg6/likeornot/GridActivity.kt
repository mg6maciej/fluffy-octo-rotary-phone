package pl.mg6.likeornot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class GridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grid_activity)
        getLikables(LikableApiProvider.get()).subscribe { it: List<Likable> ->
            val view = findViewById(R.id.likable_item_name) as TextView
            view.text = it[0].name
        }
    }
}
