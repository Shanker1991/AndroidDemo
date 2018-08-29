package com.mytaxi.android_demo;

import android.os.Build;

import com.mytaxi.android_demo.models.Driver;
import com.mytaxi.android_demo.models.User;
import com.mytaxi.android_demo.utils.SmartWait;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

import org.junit.BeforeClass;;

import java.text.SimpleDateFormat;

public class BaseTest {
    protected static Driver driver;
    protected static User adminUser;
    protected static User invalidUser;
    protected static SimpleDateFormat dateFormat;
    protected static final String strDateFormat = "yyyy-MM-dd";

    private static final int PERMISSIONS_DIALOG_DELAY = 3000;
    private static final String ALLOW_PERMISSION = "ALLOW";

    protected static enum searchKey {
        sa,
        se
    }

    @BeforeClass
    public static void initData() throws Exception {
        dateFormat = new SimpleDateFormat(strDateFormat);
        driver = new Driver("Sarah Scott", "(413) 868-2228", "", "6834 charles st", dateFormat.parse("2002-10-18"));
        adminUser = new User("crazydog335", "venture", "");
        invalidUser = new User("crazydog", "vent", "");
    }


    protected static void allowPermissionsIfNeeded() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                SmartWait.sleep(PERMISSIONS_DIALOG_DELAY);
                UiDevice device = UiDevice.getInstance(getInstrumentation());
                UiObject allowPermissions = device.findObject(new UiSelector()
                        .text(ALLOW_PERMISSION)
                        .clickable(true)
                        .checkable(false));

                if (allowPermissions.exists()) {
                    allowPermissions.click();
                }
            }
        } catch (UiObjectNotFoundException e) {
            System.out.println("There is no permissions dialog to interact with");
        }
    }
}
