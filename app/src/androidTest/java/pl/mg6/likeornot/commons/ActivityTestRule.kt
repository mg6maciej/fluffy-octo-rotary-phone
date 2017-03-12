package pl.mg6.likeornot.commons

import android.app.Activity
import android.support.test.rule.ActivityTestRule

inline fun <reified T : Activity> activityTestRule(
        crossinline before: () -> Unit,
        crossinline after: () -> Unit): ActivityTestRule<T> {

    return object : ActivityTestRule<T>(T::class.java) {

        override fun beforeActivityLaunched() = before.invoke()

        override fun afterActivityFinished() = after.invoke()
    }
}
