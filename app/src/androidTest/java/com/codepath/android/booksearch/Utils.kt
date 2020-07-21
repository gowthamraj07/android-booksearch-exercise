package com.codepath.android.booksearch

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction

class Utils {

    private companion object {
        private const val watchInterval = 200L
        private const val defaultTimeout = 3000L

        fun ViewInteraction.waitingPerform(vararg actions: ViewAction) {
            var elapsedTime = 0L

            do {
                try {
                    perform(*actions)
                    break
                } catch (error: Throwable) {
                    elapsedTime += watchInterval
                    try {
                        Thread.sleep(watchInterval)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            } while (elapsedTime <= defaultTimeout)

            if (elapsedTime > defaultTimeout) {
                perform(*actions)
            }
        }
    }
}