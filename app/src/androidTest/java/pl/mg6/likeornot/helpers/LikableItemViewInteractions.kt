package pl.mg6.likeornot.helpers

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.isNotDisplayed
import com.elpassion.android.commons.espresso.onId
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot.not
import pl.mg6.likeornot.R
import pl.mg6.likeornot.helpers.OnScreenMatcher.isOnScreenAtLeast

data class LikableItemViewInteraction(val vi: ViewInteraction)

fun onLikableItem(@IdRes id: Int): LikableItemViewInteraction {
    if (!legalIds.contains(id)) {
        throw RuntimeException("Not a likable item with id: $id")
    }
    return LikableItemViewInteraction(onView(allOf(withId(id), isOnScreenAtLeast(50))))
}

private val legalIds = intArrayOf(
        R.id.likable_item_1, R.id.likable_item_2, R.id.likable_item_3,
        R.id.likable_item_4, R.id.likable_item_5, R.id.likable_item_6,
        R.id.likable_item_7, R.id.likable_item_8, R.id.likable_item_9)

fun LikableItemViewInteraction.click() = apply { vi.click() }

fun LikableItemViewInteraction.isNotDisplayed() = apply { vi.isNotDisplayed() }

fun LikableItemViewInteraction.hasName(name: String)
        = checkLikableItemElement(R.id.likable_item_name, withText(name))

fun LikableItemViewInteraction.hasStatus(@DrawableRes imageId: Int)
        = checkLikableItemElement(R.id.likable_item_status, withImage(imageId))

fun LikableItemViewInteraction.hasNoStatus()
        = checkLikableItemElement(R.id.likable_item_status, withNoImage())

fun LikableItemViewInteraction.hasStatusOverlay()
        = checkLikableItemElement(R.id.likable_item_status_overlay, isDisplayed())

fun LikableItemViewInteraction.doesntHaveStatusOverlay()
        = checkLikableItemElement(R.id.likable_item_status_overlay, not(isDisplayed()))

private fun LikableItemViewInteraction.checkLikableItemElement(@IdRes viewId: Int, matcher: Matcher<View>) = apply {
    vi.check(matches(hasDescendant(allOf(withId(viewId), matcher))))
}

fun LikableItemViewInteraction.selectReallyLike() = selectWithId(R.id.grid_overlay_really_like)

fun LikableItemViewInteraction.selectLike() = selectWithId(R.id.grid_overlay_like)

fun LikableItemViewInteraction.selectMeh() = selectWithId(R.id.grid_overlay_meh)

fun LikableItemViewInteraction.selectDontLike() = selectWithId(R.id.grid_overlay_dont_like)

fun LikableItemViewInteraction.selectHate() = selectWithId(R.id.grid_overlay_hate)

private fun LikableItemViewInteraction.selectWithId(@IdRes viewId: Int) = apply {
    vi.click()
    onId(viewId).click()
}
