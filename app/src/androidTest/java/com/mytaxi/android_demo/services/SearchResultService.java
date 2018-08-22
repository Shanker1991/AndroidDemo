package com.mytaxi.android_demo.services;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.NoMatchingViewException;

import static android.support.test.espresso.action.ViewActions.*;

import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import com.mytaxi.android_demo.activities.MainActivity;

public class SearchResultService {

    public static void checkHeaderTitle() {
        onView(withText(R.string.title_activity_driver_profile)).check(matches(isDisplayed()));
    }

    public static void checkDriverName(String name) {
        onView(withId(R.id.textViewDriverName))
                .check(matches(withText(name)));

    }

    public static void checkDriverDate(String date) {
        onView(withId(R.id.textViewDriverDate))
                .check(matches(withText(date)));

    }

    public static void checkDriverLocation(String location) {
        onView(withId(R.id.textViewDriverLocation))
                .check(matches(withText(location)));

    }

    public static void checkDriverDetails(String name, String date, String location) {
        checkDriverDate(date);
        checkDriverName(name);
        checkDriverLocation(location);
    }

    public static boolean isDriverNamePresent() {
        try {
            onView(withId(R.id.textViewDriverName))
                    .check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }

    public static void backToSearchResult() {
        if (!DriverSearchService.isTextSearchPresnet()) {
            if (isDriverNamePresent()) {
                onView(withId(R.id.textViewDriverName))
                        .perform(pressBack());
            }
        }
    }

    public static void callDriver() {
        onView(withId(R.id.fab))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public static void bringToForeground(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);
    }
}
