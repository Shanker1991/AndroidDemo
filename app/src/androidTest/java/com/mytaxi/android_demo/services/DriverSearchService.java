package com.mytaxi.android_demo.services;

import android.app.Activity;

import static android.support.test.espresso.action.ViewActions.*;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.RootMatchers;

import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class DriverSearchService {

    public static ViewInteraction getTextSearch() {
        return onView(withId(R.id.textSearch));
    }

    public static void checkHeaderTitle() {
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
    }

    public static void checkHintText() {
        getTextSearch().check(matches(withHint(R.string.text_hint_driver)));
    }

    public static void searchWith(String searchKey) {
        getTextSearch()
                .perform(clearText())
                .perform(typeText(searchKey), closeSoftKeyboard())
                .check(matches(withText(searchKey)));
    }

    public static boolean isTextSearchPresnet() {
        try {
            getTextSearch().check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }

    public static void clearSearchText() {
        if (isTextSearchPresnet()) {
            getTextSearch().perform(clearText(), closeSoftKeyboard());
        }
    }

    public static void selectUserFromAutoComplete(String name, Activity activity) {
        onView(withText(name))
                .inRoot(RootMatchers.withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()))
                .perform(click());
    }
}
