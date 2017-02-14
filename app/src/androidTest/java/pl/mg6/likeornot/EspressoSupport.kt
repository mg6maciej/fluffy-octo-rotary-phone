package pl.mg6.likeornot

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf

fun onLikableItem(@IdRes id: Int): ViewInteraction {
    return onView(allOf(withId(id), isDisplayed()))
}

fun ViewInteraction.hasName(name: String): ViewInteraction {
    return check(matches(hasDescendant(allOf(withId(R.id.likable_item_name), withText(name)))))
}

fun ViewInteraction.hasStatus(@DrawableRes imageId: Int): ViewInteraction {
    return check(matches(hasDescendant(allOf(withId(R.id.likable_item_status), withImage(imageId)))))
}

private fun withImage(@DrawableRes imageId: Int): Matcher<View> {
    return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

        override fun matchesSafely(view: ImageView): Boolean {
            val actualState = view.drawable.constantState
            val expectedState = ContextCompat.getDrawable(view.context, imageId).constantState
            return actualState == expectedState
        }

        override fun describeTo(description: Description) {
            description.appendText("has src with id: $imageId")
        }
    }
}
