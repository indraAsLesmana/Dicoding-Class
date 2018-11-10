package com.tutor93.menampilkanarray.submission4


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.tutor93.menampilkanarray.R.id.*
import com.tutor93.menampilkanarray.submission3.Sub3Activity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainMenuTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(Sub3Activity::class.java)

    @Test
    fun testAllMenuWork(){
        /**
         * check buttomNavigation must exist
         * */
        onView(withId(bottomNavigation)).check(matches(isDisplayed()))
        /**
         * menu must be,
         * 1. last match -> eventLastFragment
         * 2. next match -> eventNextFragment
         * 3. favorite -> favoriteFragment
         *
         * - check is menu display
         * - check is clicked menu show correct view
         * */
        val titleId = arrayOf(teams, teamsNext, favorites)
        val layoutId = arrayOf(eventLastFragment, eventNextFragment, favoriteFragment)

        titleId.forEachIndexed { index, _ ->
            Thread.sleep(500)

            /*- check is menu display*/
            onView(withId(titleId[index]))
                .check(matches(isDisplayed()))
                .perform(click())

            /*- check is clicked menu show correct view*/
            onView(withId(layoutId[index]))
                .check(matches(isDisplayed()))
        }
    }

}