package io.sokolvault.wayofturtles;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.ArrayList;

import io.sokolvault.wayofturtles.model.BigGoal;
import io.sokolvault.wayofturtles.db.BigGoalDAO;
import io.sokolvault.wayofturtles.db.GoalsDatabase;

import static org.junit.Assert.*;

////@Config(constants = BuildConfig.class, sdk = 26)
////@Config(manifest = Config.DEFAULT_MANIFEST_NAME, sdk = 26, shadows = {ShadowApplication.class})
@Config(sdk = 26, manifest = "src/main/AndroidManifest.xml", shadows = {ShadowApplication.class})
@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, shadows = {ShadowApplication.class})
public class DbOperationsRoboTest {
    private Context mContext;
    private BigGoalDAO mBigGoalDAO;
    private GoalsDatabase mDatabase;
    private static final Object LOCK = new Object();
    private static String actual;

    @Before
    public void setUp() throws Exception {
        mContext = ShadowApplication.getInstance().getApplicationContext();
        mDatabase = GoalsDatabase.getInstance(mContext);
        mBigGoalDAO = mDatabase.bigGoalDAO();
    }

    @Test
    public void shouldInsertBigGoalIntoDBAndCheckForTitle() throws InterruptedException {
        BigGoal bigGoal = new BigGoal("title", "description");

        synchronized (LOCK) {
            new Thread(() -> {
                ArrayList<BigGoal> bigGoalsList;
                synchronized (LOCK) {
                    mBigGoalDAO.insertBigGoal(bigGoal);
                    bigGoalsList = (ArrayList<BigGoal>) mBigGoalDAO.getAll();
                    actual = bigGoalsList.get(bigGoalsList.size() - 1).getTitle();
                    LOCK.notify();
                }
            }).start();
            LOCK.wait();
            assertEquals(bigGoal.getTitle(), actual);
        }

    }

//    @Test
//    public void contextShouldNotBeNull() throws InterruptedException {
////        assertTrue(context.isDeviceProtectedStorage());
//        assertNotNull(context);
//
//        new Thread(() -> {
//
//        }).start();
//        wait();
//    }
}
