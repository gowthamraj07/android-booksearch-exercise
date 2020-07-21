package com.codepath.android.booksearch.utils

import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


class RecyclerViewMatcher(private var recyclerViewId: Int) {


    fun atPosition(position: Int): Matcher<View?>? {
        return atPositionOnView(position, -1)
    }

    private fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View?>? {
        return object : TypeSafeMatcher<View?>() {
            var resources: Resources? = null
            var childView: View? = null
            override fun describeTo(description: Description?) {
                var idDescription = recyclerViewId.toString()
                if (resources != null) {
                    idDescription = try {
                        resources!!.getResourceName(recyclerViewId)
                    } catch (var4: NotFoundException) {
                        String.format("%s (resource name not found)", recyclerViewId)
                    }
                }
                description?.appendText("RecyclerView with id: $idDescription at position: $position")
            }

            override fun matchesSafely(view: View?): Boolean {
                resources = view?.resources
                if (childView == null) {
                    val recyclerView = view?.rootView?.findViewById(recyclerViewId) as RecyclerView
                    if (recyclerView.id == recyclerViewId) {
                        val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                        if (viewHolder != null) {
                            childView = viewHolder.itemView
                        }
                    } else {
                        return false
                    }
                }
                return if (targetViewId == -1) {
                    view === childView
                } else {
                    val targetView: View? = childView?.findViewById(targetViewId)
                    view === targetView
                }
            }
        }
    }
}