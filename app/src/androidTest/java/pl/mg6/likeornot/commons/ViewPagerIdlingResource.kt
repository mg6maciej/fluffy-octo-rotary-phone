package pl.mg6.likeornot.commons

import android.support.annotation.IdRes
import android.support.test.espresso.IdlingResource
import android.support.test.rule.ActivityTestRule
import android.support.v4.view.ViewPager

class ViewPagerIdlingResource(rule: ActivityTestRule<*>, @IdRes pagerId: Int)
    : ViewPager.SimpleOnPageChangeListener(), IdlingResource {

    init {
        val pager = rule.activity.findViewById(pagerId) as ViewPager
        pager.addOnPageChangeListener(this)
    }

    private var idle = true
    private lateinit var callback: IdlingResource.ResourceCallback

    override fun getName() = "ViewPagerIdlingResource"

    override fun isIdleNow() = idle

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }

    override fun onPageScrollStateChanged(state: Int) {
        idle = state != ViewPager.SCROLL_STATE_SETTLING
        if (idle) {
            callback.onTransitionToIdle()
        }
    }
}
