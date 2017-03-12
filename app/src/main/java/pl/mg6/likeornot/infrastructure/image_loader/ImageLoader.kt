package pl.mg6.likeornot.infrastructure.image_loader

import android.widget.ImageView
import com.bumptech.glide.Glide

typealias LoadFromUrlFunc = (ImageView, String?) -> Unit

object ImageLoader {

    var override: LoadFromUrlFunc? = null

    fun loadFromUrl(view: ImageView, url: String?) {
        val loadFromUrlImpl = override ?: this::loadFromUrlUsingGlide
        loadFromUrlImpl(view, url)
    }

    private fun loadFromUrlUsingGlide(view: ImageView, url: String?) {
        Glide.with(view.context).load(url).into(view)
    }
}
