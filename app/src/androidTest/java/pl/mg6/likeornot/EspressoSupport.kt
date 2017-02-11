package pl.mg6.likeornot

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.AllOf.allOf
import pl.mg6.likeornot.espresso.atPosition

fun onLikableItemAtPosition(position: Int): ViewInteraction {
    return onView(allOf(withId(R.id.likable_item), atPosition(position), isDisplayed()))
}

fun ViewInteraction.hasName(name: String): ViewInteraction {
    return check(matches(hasDescendant(allOf(withId(R.id.likable_item_name), withText(name)))))
}
