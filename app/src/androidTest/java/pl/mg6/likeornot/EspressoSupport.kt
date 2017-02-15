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
import com.elpassion.android.commons.espresso.isNotDisplayed
import com.elpassion.android.commons.espresso.onId
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot.not
import pl.mg6.likeornot.OnScreenMatcher.isOnScreenAtLeast

data class LikableItemViewInteraction(val vi: ViewInteraction)

fun onLikableItem(@IdRes id: Int): LikableItemViewInteraction {
    return LikableItemViewInteraction(onView(allOf(withId(id), isOnScreenAtLeast(50))))
}

fun LikableItemViewInteraction.click(): LikableItemViewInteraction {
    vi.click()
    return this
}

fun LikableItemViewInteraction.isNotDisplayed(): LikableItemViewInteraction {
    vi.isNotDisplayed()
    return this
}

fun LikableItemViewInteraction.hasName(name: String): LikableItemViewInteraction {
    vi.check(matches(hasDescendant(allOf(withId(R.id.likable_item_name), withText(name)))))
    return this
}

fun LikableItemViewInteraction.hasStatus(@DrawableRes imageId: Int): LikableItemViewInteraction {
    vi.check(matches(hasDescendant(allOf(withId(R.id.likable_item_status), withImage(imageId)))))
    return this
}

fun LikableItemViewInteraction.hasNoStatus(): LikableItemViewInteraction {
    vi.check(matches(hasDescendant(allOf(withId(R.id.likable_item_status), withNoImage()))))
    return this
}

fun LikableItemViewInteraction.hasStatusOverlay(): LikableItemViewInteraction {
    vi.check(matches(hasDescendant(allOf(withId(R.id.likable_item_status_overlay), isDisplayed()))))
    return this
}

fun LikableItemViewInteraction.doesntHaveStatusOverlay(): LikableItemViewInteraction {
    vi.check(matches(hasDescendant(allOf(withId(R.id.likable_item_status_overlay), not(isDisplayed())))))
    return this
}

fun LikableItemViewInteraction.selectReallyLike(): LikableItemViewInteraction {
    return selectWithId(R.id.grid_overlay_really_like)
}

fun LikableItemViewInteraction.selectLike(): LikableItemViewInteraction {
    return selectWithId(R.id.grid_overlay_like)
}

fun LikableItemViewInteraction.selectMeh(): LikableItemViewInteraction {
    return selectWithId(R.id.grid_overlay_meh)
}

fun LikableItemViewInteraction.selectDontLike(): LikableItemViewInteraction {
    return selectWithId(R.id.grid_overlay_dont_like)
}

fun LikableItemViewInteraction.selectHate(): LikableItemViewInteraction {
    return selectWithId(R.id.grid_overlay_hate)
}

private fun LikableItemViewInteraction.selectWithId(viewId: Int): LikableItemViewInteraction {
    vi.click()
    onId(viewId).click()
    return this
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
