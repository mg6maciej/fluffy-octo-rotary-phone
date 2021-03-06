package pl.mg6.likeornot.infrastructure.logger

import android.util.Log
import com.crashlytics.android.Crashlytics
import com.google.firebase.crash.FirebaseCrash

typealias LogErrorFunc = (String, String, Throwable) -> Unit

object ErrorLogger {

    var override: LogErrorFunc? = null

    fun logError(tag: String, message: String, error: Throwable) {
        val logErrorImpl = override ?: this::logcatFirebaseCrashlytics
        logErrorImpl(tag, message, error)
    }

    private fun logcatFirebaseCrashlytics(tag: String, message: String, error: Throwable) {
        Log.e(tag, message, error)
        FirebaseCrash.log(message)
        FirebaseCrash.report(error)
        Crashlytics.logException(error)
    }
}
