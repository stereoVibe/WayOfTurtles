package io.sokolvault.wayofturtles;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.sokolvault.wayofturtles.model.BigGoal;
import io.sokolvault.wayofturtles.db.BigGoalDAO;
import io.sokolvault.wayofturtles.db.GoalsDatabase;

@RunWith(AndroidJUnit4.class)
public class DbOperationsInstTest {
    private BigGoalDAO mBigGoalDAO;
    private GoalsDatabase mDatabase;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        mDatabase = Room.inMemoryDatabaseBuilder(context, GoalsDatabase.class).build();
        mBigGoalDAO = mDatabase.bigGoalDAO();
    }

    @Test
    public void writeBigGoal(){
        BigGoal bigGoal = new BigGoal("testTitle", "testDescription");
        mBigGoalDAO.insertBigGoal(bigGoal);
        assertNotNull(mBigGoalDAO.getAll());
    }
}
