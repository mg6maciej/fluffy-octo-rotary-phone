package pl.mg6.likeornot

import android.support.annotation.DrawableRes
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import org.hamcrest.Description
import org.hamcrest.Matcher

fun ViewInteraction.swipeLeft(): ViewInteraction {
    return perform(ViewActions.swipeLeft())
}

fun ViewInteraction.swipeRight(): ViewInteraction {
    return perform(ViewActions.swipeRight())
}

fun withImage(@DrawableRes imageId: Int): Matcher<View> {
    return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

        override fun matchesSafely(view: ImageView): Boolean {
            val drawable = view.drawable ?: return false
            val actualState = drawable.constantState
            val expectedState = ContextCompat.getDrawable(view.context, imageId).constantState
            return actualState == expectedState
        }

        override fun describeTo(description: Description) {
            description.appendText("has src with id: $imageId")
        }
    }
}

fun withNoImage(): Matcher<View> {
    return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

        override fun matchesSafely(view: ImageView): Boolean {
            return view.drawable == null
        }

        override fun describeTo(description: Description) {
            description.appendText("has no src set")
        }
    }
}
