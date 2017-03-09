package pl.mg6.likeornot.grid.view.impl

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import pl.mg6.likeornot.R
import pl.mg6.likeornot.commons.view.loadFromUrl
import pl.mg6.likeornot.grid.entity.Likable

class OverlayView(private val activity: Activity, private val clickCallback: (Likable, Int) -> Unit) {

    fun showLikable(likable: Likable) {
        val overlay = activity.findViewById(R.id.grid_overlay)
        overlay.setOnTouchListener { _, _ -> true }
        overlay.visibility = View.VISIBLE
        val name = overlay.findViewById(R.id.grid_overlay_name) as TextView
        name.text = likable.name
        val image = overlay.findViewById(R.id.grid_overlay_image) as ImageView
        image.loadFromUrl(likable.image)
        val handleClick: (View) -> Unit = {
            overlay.visibility = View.GONE
            clickCallback.invoke(likable, idToImage.getValue(it.id))
        }
        overlay.findViewById(R.id.grid_overlay_really_like).setOnClickListener(handleClick)
        overlay.findViewById(R.id.grid_overlay_like).setOnClickListener(handleClick)
        overlay.findViewById(R.id.grid_overlay_meh).setOnClickListener(handleClick)
        overlay.findViewById(R.id.grid_overlay_dont_like).setOnClickListener(handleClick)
        overlay.findViewById(R.id.grid_overlay_hate).setOnClickListener(handleClick)
    }

    companion object {

        private val idToImage = mapOf(
                R.id.grid_overlay_really_like to R.drawable.really_like,
                R.id.grid_overlay_like to R.drawable.like,
                R.id.grid_overlay_meh to R.drawable.meh,
                R.id.grid_overlay_dont_like to R.drawable.dont_like,
                R.id.grid_overlay_hate to R.drawable.hate
        )
    }
}
