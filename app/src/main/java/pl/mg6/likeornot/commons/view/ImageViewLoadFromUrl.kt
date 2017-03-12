package pl.mg6.likeornot.commons.view

import android.widget.ImageView
import com.bumptech.glide.Glide

typealias LoadFromUrlFunc = ImageView.(String?) -> Unit

var loadFromUrlOverride: LoadFromUrlFunc? = null

fun ImageView.loadFromUrl(url: String?) {
    val loadFromUrlImpl = loadFromUrlOverride ?: ImageView::loadFromUrlUsingGlide
    loadFromUrlImpl(this, url)
}

private fun ImageView.loadFromUrlUsingGlide(url: String?) {
    Glide.with(context).load(url).into(this)
}
