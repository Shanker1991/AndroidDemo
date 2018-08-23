package com.mytaxi.android_demo.tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.BaseTest;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.services.LoginService;
import com.mytaxi.android_demo.services.NavMenuService;
import com.mytaxi.android_demo.services.DriverSearchService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginTest extends BaseTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    @After
    public void setup() {
        allowPermissionsIfNeeded();
        if (!LoginService.isLoginActivity()) {
            if (NavMenuService.isMenuPresnt()) {
                NavMenuService.logout();
            }
        }
    }

    @Test
    public void sucessfulLogin() {
        LoginService.loginAs(adminUser.getUsername(), adminUser.getSalt());
        DriverSearchService.waitForDriverSearchActivity();
        DriverSearchService.checkHeaderTitle();
        NavMenuService.goToNavigableMenu();
        NavMenuService.checkUserName(adminUser.getUsername());
        NavMenuService.closeNavigableMenu();
    }

    @Test
    public void failedLoginWithIncorrectUsernameAndPassword() {
        LoginService.loginAs(invalidUser.getUsername(), invalidUser.getSalt());
        LoginService.checkFailedLoginMessage();
    }

    @Test
    public void failedLoginWithBlankUsername() {
        LoginService.loginAs("", invalidUser.getSalt());
        LoginService.checkFailedLoginMessage();
    }

    @Test
    public void failedLoginWithBlankPassword() {
        LoginService.loginAs(adminUser.getUsername(), "");
        LoginService.checkFailedLoginMessage();
    }

    @Test
    public void failedLoginWithIncorrectUsername() {
        LoginService.loginAs(invalidUser.getUsername(), adminUser.getSalt());
        LoginService.checkFailedLoginMessage();
    }

    @Test
    public void failedLoginWithIncorrectPassword() {
        LoginService.loginAs(adminUser.getUsername(), invalidUser.getSalt());
        LoginService.checkFailedLoginMessage();
    }
}
