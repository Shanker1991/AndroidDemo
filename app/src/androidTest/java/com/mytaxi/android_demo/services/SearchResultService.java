package com.mytaxi.android_demo.services;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;

import static android.support.test.espresso.action.ViewActions.*;

import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.utils.SmartWait;

public class SearchResultService {

    public static ViewInteraction getDriverNameField() {
        return onView(withId(R.id.textViewDriverName));
    }


    public static ViewInteraction getDriverDateField() {
        return onView(withId(R.id.textViewDriverDate));
    }

    public static ViewInteraction getDriverLocationField() {
        return onView(withId(R.id.textViewDriverLocation));
    }

    public static ViewInteraction getCallButton() {
        return onView(withId(R.id.fab));
    }


    public static void checkHeaderTitle() {
        onView(withText(R.string.title_activity_driver_profile)).check(matches(isDisplayed()));
    }

    public static void checkDriverName(String name) {
        getDriverNameField()
                .check(matches(withText(name)));

    }

    public static void checkDriverDate(String date) {
        getDriverDateField()
                .check(matches(withText(date)));

    }

    public static void checkDriverLocation(String location) {
        getDriverLocationField()
                .check(matches(withText(location)));

    }

    public static void checkDriverDetails(String name, String date, String location) {
        waitForDriverResultActivity();
        checkDriverDate(date);
        checkDriverName(name);
        checkDriverLocation(location);
    }

    public static boolean isDriverNamePresent() {
        try {
            getDriverNameField()
                    .check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }

    public static void backToSearchResult() {
        if (!DriverSearchService.isTextSearchPresnet()) {
            if (isDriverNamePresent()) {
                getDriverNameField()
                        .perform(pressBack());
            }
        }
    }

    public static void callDriver() {
        getCallButton()
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public static void bringToForeground(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);
    }

    public static void waitForDriverResultActivity(){
        SmartWait.waitUntilViewDisplayed(getDriverNameField());
    }
}
