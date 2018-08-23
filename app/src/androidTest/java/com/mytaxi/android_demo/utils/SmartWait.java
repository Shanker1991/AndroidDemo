package com.mytaxi.android_demo.utils;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;

public class SmartWait {

    private static final int MAX_TIMEOUT_WAIT = 20000;
    private static final int TRY_INTERVAL = 2000;

    public static void waitUntilViewDisplayed(ViewInteraction viewInteraction) throws Exception {
        int timeout = MAX_TIMEOUT_WAIT;
        while (timeout != 0) {
            try {
                viewInteraction.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
                break;
            } catch (NoMatchingViewException e) {
                System.out.println("Remaining Wait time");
                Thread.sleep(TRY_INTERVAL);
                timeout -= TRY_INTERVAL;
            }
        }
    }
}
