package pl.mg6.likeornot

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.elpassion.android.commons.espresso.click
import com.elpassion.android.commons.espresso.isNotDisplayed
import com.elpassion.android.commons.espresso.onId
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot
import pl.mg6.likeornot.OnScreenMatcher.isOnScreenAtLeast

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
    vi.check(matches(hasDescendant(allOf(withId(R.id.likable_item_status_overlay), IsNot.not(isDisplayed())))))
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
