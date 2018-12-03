package com.tutor93.menampilkanarray.submission4

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.tutor93.menampilkanarray.R.id.*
import com.tutor93.menampilkanarray.data.Favorite
import com.tutor93.menampilkanarray.database
import com.tutor93.menampilkanarray.main.MainActivity
import org.jetbrains.anko.db.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainMenuTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    private val menuId   = arrayOf(teams, teamsNext, favorites)
    private val layoutId = arrayOf(matchViewLayout, teamViewLayout, matchViewLayout)
    private val rvId     = arrayOf(matchViewRecyclerview, teamViewRecyclerview, favoriteViewSRecyclerview)

    @Before
    fun setUp() {
        activityRule.activity.database.use {
            dropTable(Favorite.TABLE_FAVORITE, false)
            createTable(
                Favorite.TABLE_FAVORITE, false,
                "ID_" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                "TEAM_ID" to TEXT + UNIQUE,
                "TEAM_NAME" to TEXT,
                "TEAM_BADGE" to TEXT,
                "TEAM_EVENT" to TEXT,
                "TEAM_AWAY_BADGE" to TEXT,
                "TEAM_HOME_BADGE" to TEXT
            )
        }
        /**
         * make sure table is clean before test
         * */
    }

    @Test
    fun testBottomMenu(){
        /**
         * check buttomNavigation must exist
         * */
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
    }
    @Test
    fun testAllMenuWork(){
        /**
         * menu and layout must be:
         * 1. match      -> eventLastFragment
         * 2. team       -> eventNextFragment
         * 3. favorite   -> favoriteFragment
         *
         * case:
         * - check is menu display
         * - check is clicked menu show correct layout
         * */
        menuId.forEachIndexed { index, _ ->
            Thread.sleep(500)

            /*- check is menu display and click*/
            onView(withId(menuId[index]))
                .check(matches(isDisplayed()))
                .perform(click())

            /*- check is clicked menu show correct layout*/
            onView(withId(layoutId[index]))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun testFavoriteUnfavorite() {
        val lastMatchMenu = menuId[1]
        val recyclerViewLastMatch = rvId[1]
        val repeatTime = 2  //repeat as you like
        var isAddToFavorite = false

        onView(withId(lastMatchMenu))
            .perform(click())

        Thread.sleep(3000)
        /**
         * for first time recyclerViewLastMatch is INVISIBLE to user, to show loading progress.
         * if this test failed, try increase thread sleep before this code thanks!.
         * */
        onView(withId(recyclerViewLastMatch))
            .check(matches(isDisplayed()))

        repeat(repeatTime){
            onView(withId(recyclerViewLastMatch))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

            onView(withId(recyclerViewLastMatch))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

            onView(withId(layDetailContainer))
                .check(matches(isDisplayed()))

            Thread.sleep(2000)
            /**
             * this sleep is required caused by make validation to load HOME and AWAY team URL image
             * to success save favorite.
             * if failed try increase thread sleep before this code thanks!.
             * */

            onView(withId(add_to_favorite))
                .perform(click())

            if (isAddToFavorite){
                onView(withText("Removed from favorite"))
                    .check(matches(isDisplayed()))
                isAddToFavorite = false
            }else{
                onView(withText("Added to favorite"))
                    .check(matches(isDisplayed()))
                isAddToFavorite = true
            }

            pressBack()
        }

    }

}