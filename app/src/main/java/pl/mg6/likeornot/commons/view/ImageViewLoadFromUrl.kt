package pl.mg6.likeornot.commons.view

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadFromUrl(url: String?) = Glide.with(context).load(url).into(this)
