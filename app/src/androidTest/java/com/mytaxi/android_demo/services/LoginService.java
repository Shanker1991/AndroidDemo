package com.mytaxi.android_demo.services;

import android.support.test.espresso.NoMatchingViewException;

import static android.support.test.espresso.assertion.ViewAssertions.*;

import static android.support.test.espresso.action.ViewActions.*;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;

import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class LoginService {

    public static ViewInteraction getUsernameField() {
        return onView(ViewMatchers.withId(R.id.edt_username));
    }

    public static ViewInteraction getPasswordFeild() {
        return onView(ViewMatchers.withId(R.id.edt_password));
    }

    public static ViewInteraction getLoginButton() {
        return onView(withId(R.id.btn_login));
    }

    public static ViewInteraction getLoginErrorLabel() {
        return onView(withText(R.string.message_login_fail));
    }

    public static void setUsername(String username) {
        getUsernameField()
                .perform(click())
                .perform(clearText())
                .perform(typeText(username), closeSoftKeyboard())
                .check(matches(withText(username)));

    }

    public static void setPassword(String password) {
        getPasswordFeild()
                .perform(click())
                .perform(clearText())
                .perform(typeText(password), closeSoftKeyboard())
                .check(matches(withText(password)));
    }

    public static void clickLogin() {
        getLoginButton().perform(click());
    }

    public static void loginAs(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLogin();
    }

    public static boolean isLoginActivity() {
        try {
            getUsernameField().check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }

    public static void checkFailedLoginMessege() {
        getLoginErrorLabel()
                .check(matches(isDisplayed()));
    }
}
