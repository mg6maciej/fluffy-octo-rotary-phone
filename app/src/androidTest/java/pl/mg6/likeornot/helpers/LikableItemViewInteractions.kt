package pl.mg6.likeornot.helpers

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.isNotDisplayed
import com.elpassion.android.commons.espresso.matchers.withAnyImage
import com.elpassion.android.commons.espresso.matchers.withImage
import com.elpassion.android.commons.espresso.onId
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot.not
import pl.mg6.likeornot.R
import pl.mg6.likeornot.helpers.OnScreenMatcher.isOnScreenAtLeast
import kotlin.LazyThreadSafetyMode.NONE

class LikableItemViewInteraction(@IdRes private val id: Int) {

    private val likableItemMatcher by lazy(NONE) { allOf(withId(id), isOnScreenAtLeast(50)) }

    fun click() = apply { onView(likableItemMatcher).click() }

    fun isNotDisplayed() = apply { onView(likableItemMatcher).isNotDisplayed() }

    fun hasName(name: String)
            = checkLikableItemElement(R.id.likable_item_name, withText(name))

    fun hasStatus(@DrawableRes imageId: Int)
            = checkLikableItemElement(R.id.likable_item_status, withImage(imageId))

    fun doesNotHaveStatus()
            = checkLikableItemElement(R.id.likable_item_status, not(withAnyImage()))

    fun hasStatusOverlay()
            = checkLikableItemElement(R.id.likable_item_status_overlay, isDisplayed())

    fun doesNotHaveStatusOverlay()
            = checkLikableItemElement(R.id.likable_item_status_overlay, not(isDisplayed()))

    private fun checkLikableItemElement(@IdRes viewId: Int, matcher: Matcher<View>) = apply {
        onView(allOf(withId(viewId), isDescendantOfA(likableItemMatcher))).check(matches(matcher))
    }

    fun selectReallyLike() = selectWithId(R.id.grid_overlay_really_like)

    fun selectLike() = selectWithId(R.id.grid_overlay_like)

    fun selectMeh() = selectWithId(R.id.grid_overlay_meh)

    fun selectDontLike() = selectWithId(R.id.grid_overlay_dont_like)

    fun selectHate() = selectWithId(R.id.grid_overlay_hate)

    private fun selectWithId(@IdRes viewId: Int) = apply {
        click()
        onId(viewId).click()
    }
}

fun onLikableItem(@IdRes id: Int): LikableItemViewInteraction {
    if (!legalIds.contains(id)) {
        throw RuntimeException("Not a likable item with id: $id")
    }
    return LikableItemViewInteraction(id)
}

private val legalIds = intArrayOf(
        R.id.likable_item_1, R.id.likable_item_2, R.id.likable_item_3,
        R.id.likable_item_4, R.id.likable_item_5, R.id.likable_item_6,
        R.id.likable_item_7, R.id.likable_item_8, R.id.likable_item_9)
