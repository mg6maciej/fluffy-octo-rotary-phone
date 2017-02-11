package pl.mg6.likeornot.espresso

import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun atPosition(position: Int): Matcher<in View> {
    return object : TypeSafeMatcher<View>(View::class.java) {
        override fun matchesSafely(item: View): Boolean {
            val parent = item.parent as ViewGroup
            return parent.getChildAt(position) === item
        }

        override fun describeTo(description: Description) {
            description.appendText("is at position #" + position)
        }
    }
}
