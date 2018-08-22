package com.mytaxi.android_demo.services;

import android.support.test.espresso.NoMatchingViewException;

import static android.support.test.espresso.action.ViewActions.*;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.DrawerMatchers;
import android.view.Gravity;

import com.mytaxi.android_demo.R;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class NavMenuService {

    public static ViewInteraction getMenu() {
        return onView(withId(R.id.drawer_layout));
    }

    public static ViewInteraction getUsernameLabel() {
        return onView(withId(R.id.nav_username));
    }

    public static void goToNavigableMenu() {
        getMenu()
                .check(matches(DrawerMatchers.isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
    }

    public static void closeNavigableMenu() {
        getMenu()
                .check(matches(DrawerMatchers.isOpen(Gravity.LEFT)))
                .perform(DrawerActions.close());
    }

    public static void checkUserName(String username) {
        getUsernameLabel().check(matches(withText(username)));
    }

    public static boolean isMenuPresnt() {
        try {
            getMenu().check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }


    public static void logout() {
        if (isMenuPresnt()) {
            goToNavigableMenu();
            onView(withText(R.string.text_item_title_logout))
                    .perform(click());
        }
    }
}
