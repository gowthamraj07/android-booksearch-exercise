package com.codepath.android.booksearch

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.codepath.android.booksearch.activities.BookListActivity
import com.codepath.android.booksearch.utils.TestUtils.withRecyclerView
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BookActivityUITest {

    @get:Rule
    val activityRule = ActivityTestRule(BookListActivity::class.java)

    @Test
    fun shouldNavigateToDetailActivityWhen1ItemClicked() {
        Thread.sleep(5000)
        onView(withRecyclerView(R.id.rvBooks).atPosition(1)).perform(click())

        onView(allOf(instanceOf<Any>(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withResourceName("action_bar"))))
                .check(matches(ViewMatchers.withText("BookDetailActivity")))
    }

    @Test
    @Ignore("Failing test case -> check with ananth")
    fun shouldNavigateToDetailActivityWhen15ItemClicked() {
        Thread.sleep(5000)
        getListItemAt(15).swipeDown()
        onView(withRecyclerView(R.id.rvBooks).atPosition(15)).perform(click())

        onView(allOf(instanceOf<Any>(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withResourceName("action_bar"))))
                .check(matches(ViewMatchers.withText("BookDetailActivity")))
    }

    private fun getListItemAt(position: Int): Matcher<View>? {
        return allOf(childAtPosition(withId(R.id.rvBooks), position), isDisplayed())
    }

    private fun Matcher<View>?.swipeDown() {
        onView(this).waitingPerform(ViewActions.swipeDown())
    }

    private fun childAtPosition(
            parentMatcher: Matcher<in View?>?, position: Int): Matcher<in View>? {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher?.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher!!.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}

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
