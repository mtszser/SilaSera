package com.example.silasera.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.silasera.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    @get:Rule val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkActivityVisible() {
        onView(withId(R.id.mainActivity_layout))
            .check(matches(isDisplayed()))
    }

//    @Test
//    fun setValuesOnScreen() {
//        onView(withId(R.id.login_email))
//            .perform(typeText("test@gmail.com"))
//        onView(withId(R.id.login_password))
//            .perform(typeText("123456"))
//        onView(withId(R.id.login_button))
//            .perform(scrollTo(), click())
//
//    }
}