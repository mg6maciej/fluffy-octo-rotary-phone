package pl.mg6.likeornot.grid.entity

import android.support.annotation.DrawableRes
import pl.mg6.likeornot.R

enum class Status(@DrawableRes val imageId: Int) {

    REALLY_LIKE(R.drawable.really_like),
    LIKE(R.drawable.like),
    MEH(R.drawable.meh),
    DONT_LIKE(R.drawable.dont_like),
    HATE(R.drawable.hate),
}
