package com.codepath.android.booksearch

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.codepath.android.booksearch.activities.BookListActivity
import com.codepath.android.booksearch.utils.TestUtils.withRecyclerView
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BookActivityUITest {

    @get:Rule
    val activityRule = ActivityTestRule(BookListActivity::class.java)


    @Test
    fun shouldNavigateToDetailActivity() {
        //onView(withId(R.id.text_view_rocks)).check(matches(withText(
         //       mActivityRule.getActivity().getString(R.string.android_testing_rocks))));

        Thread.sleep(4000)
        onView(withRecyclerView(R.id.rvBooks).atPosition(1)).perform(click())

        onView(allOf(CoreMatchers.instanceOf<Any>(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withResourceName("action_bar"))))
                .check(matches(ViewMatchers.withText("BookDetailActivity")))

        //onView(withId(R.id.team_name)).check(matches(isDisplayed()))
    }

    fun Matcher<View?>?.swipeDown() {
        //onView(this).wait
    }

    private fun childAtPosition(
            parentMatcher: Matcher<in View?>?, position: Int): Any {

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