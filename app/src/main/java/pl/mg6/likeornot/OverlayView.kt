package pl.mg6.likeornot

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class OverlayView(private val activity: Activity) {

    fun showLikable(likable: Likable) {
        val overlay = activity.findViewById(R.id.grid_overlay)
        overlay.setOnTouchListener { _, _ -> true }
        overlay.visibility = View.VISIBLE
        val name = overlay.findViewById(R.id.grid_overlay_name) as TextView
        name.text = likable.name
        val image = overlay.findViewById(R.id.grid_overlay_image) as ImageView
        Glide.with(activity).load(likable.image).into(image)
        overlay.findViewById(R.id.grid_overlay_like).setOnClickListener {
            overlay.visibility = View.GONE
        }
    }
}
