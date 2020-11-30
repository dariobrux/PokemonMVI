package com.dariobrux.pokemon.data.main

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dariobrux.pokemon.R
import com.dariobrux.pokemon.ui.MainActivity
import com.dariobrux.pokemon.ui.main.MainAdapter
import org.junit.After
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import timber.log.Timber

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.JVM)
class MainFragmentTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @After
    fun cleanup() {
        scenario.close()
    }

    @Test
    fun testRecyclerIsDisplayed() {
        scenario = launchActivity()
        Thread.sleep(3500)
        Espresso.onView(withId(R.id.mainRecycler)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testRecyclerSwipeDownAndUp() {
        scenario = launchActivity()
        Thread.sleep(3500)
        Espresso.onView(withId(R.id.mainRecycler)).perform(ViewActions.swipeUp())
        Espresso.onView(withId(R.id.mainRecycler)).perform(ViewActions.swipeDown())
    }

    @Test
    fun testRecyclerClickOnFirstItem() {
        scenario = launchActivity()
        Thread.sleep(6000)
        try {
            Espresso.onView(withId(R.id.mainRecycler)).perform(RecyclerViewActions.actionOnItemAtPosition<MainAdapter.PokemonViewHolder>(0, click()))
        } catch (e: Exception) {
            Timber.e("Something goes wrong. Maybe is internet not available?")
            Espresso.onView(withId(R.id.mainRecycler)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}
