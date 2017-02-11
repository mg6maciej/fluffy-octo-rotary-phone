package pl.mg6.likeornot.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet

class SquareViewPager : ViewPager {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth)
    }
}
