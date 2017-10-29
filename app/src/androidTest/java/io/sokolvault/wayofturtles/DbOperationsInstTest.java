package io.sokolvault.wayofturtles;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.sokolvault.wayofturtles.db.JobDAO;
import io.sokolvault.wayofturtles.model.BigGoal;
import io.sokolvault.wayofturtles.db.BigGoalDAO;
import io.sokolvault.wayofturtles.db.GoalsDatabase;
import io.sokolvault.wayofturtles.model.Job;

@RunWith(AndroidJUnit4.class)
public class DbOperationsInstTest {
    private BigGoal mBigGoal;
    private BigGoalDAO mBigGoalDAO;
    private JobDAO mJobDAO;
    private GoalsDatabase mDatabase;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        mDatabase = Room.inMemoryDatabaseBuilder(context, GoalsDatabase.class).build();
        mBigGoalDAO = mDatabase.bigGoalDAO();
        mJobDAO = mDatabase.jobsDAO();
        mBigGoal = new BigGoal("testTitle", "testDescription");
    }

    @Test
    public void writeBigGoal(){
        mBigGoalDAO.insertBigGoal(mBigGoal);
        assertNotNull(mBigGoalDAO.getAll());
    }

    @Test
    public void insertJob(){
        Job job;
        mBigGoalDAO.insertBigGoal(mBigGoal);
        if (mBigGoal.getId() != null) {
            job = Job.newBuilder().
                    setTitle("testTitle")
                    .setCompositeGoalID(mBigGoal.getId())
                    .setTasksQuantity(10)
                    .build();
            mJobDAO.insertSubGoal(job);
        }
        assertNotNull(mJobDAO.getAll());
    }

    @Test
    public void insertingTest(){
        Job job;
        int bigGoalId;
        List<Job> jobsList;
        mBigGoalDAO.insertBigGoal(mBigGoal);
        mBigGoalDAO.insertBigGoal(mBigGoal);
        mBigGoal = mBigGoalDAO.getBigGoalById(2);
        bigGoalId = mBigGoal.getId();
        job = Job.newBuilder()
                .setId(0)
                .setTitle("test")
                .setCompositeGoalID(bigGoalId)
                .setTasksQuantity(10)
                .build();
        mJobDAO.insertSubGoal(job);
        jobsList = mJobDAO.getAll();

        assertEquals(bigGoalId, jobsList.get(0).getCompositeGoalID());
//        assertEquals(2, mBigGoalDAO.getAll().size());
    }
}
