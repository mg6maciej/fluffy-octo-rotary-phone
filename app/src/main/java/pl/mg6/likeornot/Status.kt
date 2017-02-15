package pl.mg6.likeornot

import android.support.annotation.DrawableRes

enum class Status(@DrawableRes val imageId: Int) {

    REALLY_LIKE(R.drawable.really_like),
    LIKE(R.drawable.like),
    MEH(R.drawable.meh),
    DONT_LIKE(R.drawable.dont_like),
    HATE(R.drawable.hate),
}
