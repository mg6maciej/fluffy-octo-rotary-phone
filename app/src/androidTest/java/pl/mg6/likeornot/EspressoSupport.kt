package pl.mg6.likeornot

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.onId
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot.not
import pl.mg6.likeornot.OnScreenMatcher.isOnScreenAtLeast

fun onLikableItem(@IdRes id: Int): ViewInteraction {
    return onView(allOf(withId(id), isOnScreenAtLeast(50)))
}

fun ViewInteraction.hasName(name: String): ViewInteraction {
    return check(matches(hasDescendant(allOf(withId(R.id.likable_item_name), withText(name)))))
}

fun ViewInteraction.hasStatus(@DrawableRes imageId: Int): ViewInteraction {
    return check(matches(hasDescendant(allOf(withId(R.id.likable_item_status), withImage(imageId)))))
}

fun ViewInteraction.hasNoStatus(): ViewInteraction {
    return check(matches(hasDescendant(allOf(withId(R.id.likable_item_status), withNoImage()))))
}

fun ViewInteraction.hasStatusOverlay(): ViewInteraction {
    return check(matches(hasDescendant(allOf(withId(R.id.likable_item_status_overlay), isDisplayed()))))
}

fun ViewInteraction.doesntHaveStatusOverlay(): ViewInteraction {
    return check(matches(hasDescendant(allOf(withId(R.id.likable_item_status_overlay), not(isDisplayed())))))
}

fun ViewInteraction.selectReallyLike(): ViewInteraction {
    return selectWithId(R.id.grid_overlay_really_like)
}

fun ViewInteraction.selectLike(): ViewInteraction {
    return selectWithId(R.id.grid_overlay_like)
}

fun ViewInteraction.selectMeh(): ViewInteraction {
    return selectWithId(R.id.grid_overlay_meh)
}

fun ViewInteraction.selectDontLike(): ViewInteraction {
    return selectWithId(R.id.grid_overlay_dont_like)
}

fun ViewInteraction.selectHate(): ViewInteraction {
    return selectWithId(R.id.grid_overlay_hate)
}

private fun ViewInteraction.selectWithId(viewId: Int): ViewInteraction {
    val interaction = click()
    onId(viewId).click()
    return interaction
}

fun ViewInteraction.swipeLeft(): ViewInteraction {
    return perform(ViewActions.swipeLeft())
}

fun ViewInteraction.swipeRight(): ViewInteraction {
    return perform(ViewActions.swipeRight())
}

private fun withImage(@DrawableRes imageId: Int): Matcher<View> {
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

private fun withNoImage(): Matcher<View> {
    return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

        override fun matchesSafely(view: ImageView): Boolean {
            return view.drawable == null
        }

        override fun describeTo(description: Description) {
            description.appendText("has no src set")
        }
    }
}
