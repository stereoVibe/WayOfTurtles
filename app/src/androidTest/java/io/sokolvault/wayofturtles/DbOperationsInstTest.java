package io.sokolvault.wayofturtles;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.sokolvault.wayofturtles.db.JobDAO;
import io.sokolvault.wayofturtles.db.model.BigGoalEntity;
import io.sokolvault.wayofturtles.db.BigGoalDAO;
import io.sokolvault.wayofturtles.db.GoalsDatabase;
import io.sokolvault.wayofturtles.db.model.JobEntity;
import io.sokolvault.wayofturtles.model.AbstractGoal;

@RunWith(AndroidJUnit4.class)
public class DbOperationsInstTest {

    private BigGoalEntity BIG_GOAL =
            new BigGoalEntity("title", "description");

    private final JobEntity JOB = JobEntity.newBuilder()
            .setId(1)
            .setCompositeGoalID(1)
            .setTitle("Job Title")
            .setDescription("Job description")
            .setTasksQuantity(10)
            .build();


    private BigGoalDAO mBigGoalDAO;
    private JobDAO mJobDAO;
    private GoalsDatabase mDatabase;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        mDatabase = Room.inMemoryDatabaseBuilder(context, GoalsDatabase.class).build();
        mBigGoalDAO = mDatabase.bigGoalDAO();
        mJobDAO = mDatabase.jobsDAO();

//        BIG_GOAL.setId(1);
        mDatabase.bigGoalDAO().insertBigGoal(BIG_GOAL);
    }

    @After
    public void closeDb(){
        mDatabase.close();
    }

//    CRUD tests

//  INSERT & READ tests
    @Test
    public void insertAndGetBigGoalEntityTest(){
        BIG_GOAL.setId(2); // Setting the ID to be sure it will match auto generating
        mBigGoalDAO.insertBigGoal(BIG_GOAL);

        BigGoalEntity dbBigGoal = mBigGoalDAO.getAll().get(1);
        assertEquals(2, mBigGoalDAO.getAll().size());

        performCoreAsserts(BIG_GOAL, dbBigGoal);
    }

    @Test
    public void insertAndGetJobEntityTest(){

        mJobDAO.insertJobSubGoal(JOB);
        JobEntity dbJob =
                getFirstGoalEntityAndAssertForSize(mJobDAO.getAll());

        performCoreAsserts(JOB, dbJob);
        assertEquals(JOB.getCompositeGoalID(), dbJob.getCompositeGoalID());
        assertEquals(JOB.getTasksQuantity(), dbJob.getTasksQuantity());
        assertEquals(JOB.getCompletedTasksQuantity(), dbJob.getCompletedTasksQuantity());
    }

    @Test
    public void insertAndGetTaskEntityTest(){

    }
//  UPDATE & READ
    @Test
    public void updateAndGetBigGoalEntityTest(){
        BIG_GOAL.setId(1); // Setting the ID to be sure it will match auto generating
        performCoreUpdates(BIG_GOAL);
        mBigGoalDAO.updateBigGoal(BIG_GOAL);

        BigGoalEntity dbBigGoal = mBigGoalDAO.getAll().get(0);

        performCoreAsserts(BIG_GOAL, dbBigGoal);
    }

    @Test
    public void updateAndGetJobEntityTest(){
        /**
         * ID for BigGoal should be updated, as it matches the compositeGoalID in
         * all SubGoals (JobEntity here), so it must exist. Because of auto generated id for BigGoal,
         * we can't updated it in DB, so it needs another insert - {@link #addSecondBigGoal()}
        **/
        addSecondBigGoal();

        mJobDAO.insertJobSubGoal(JOB); // UPDATE needs something to update %)

        performCoreUpdates(JOB);
        JOB.setCompositeGoalID(2);
        JOB.setTasksQuantity(20);
        JOB.setCompletedTasksQuantity(20);

        mJobDAO.updateJobSubGoal(JOB);

        JobEntity dbJob =
                getFirstGoalEntityAndAssertForSize(mJobDAO.getAll());

        performCoreAsserts(JOB, dbJob);
        assertEquals(JOB.getCompositeGoalID(), dbJob.getCompositeGoalID());
        assertEquals(JOB.getTasksQuantity(), dbJob.getTasksQuantity());
        assertEquals(JOB.getCompletedTasksQuantity(), dbJob.getCompletedTasksQuantity());
    }

    @Test
    public void updateAndGetAndGetTaskEntityTest(){

    }

//  DELETE & READ (failed to get)
    @Test
    public void deleteAndFailToGetBigGoalEntityTest(){

    }

    @Test
    public void deleteAndFailToGetJobEntityTest(){

    }

    @Test
    public void deleteAndFailToGetTaskEntityTest(){

    }

    private void addSecondBigGoal() {
        BIG_GOAL.setId(2); // setting new id for local variable
        mBigGoalDAO.insertBigGoal(BIG_GOAL);
        assertNotNull(mBigGoalDAO.getBigGoalById(2));
    }

    private <T extends AbstractGoal> T getFirstGoalEntityAndAssertForSize(List<T> goals){
        assertThat(goals.size(), is(1) );
        return goals.get(0);
    }

//  Bundle of asserts matches all Goals in the app.
    private <T extends AbstractGoal> void performCoreAsserts(T mockGoal, T actualGoal){
        assertEquals(mockGoal.getId(), actualGoal.getId());
        assertEquals(mockGoal.getTitle(), actualGoal.getTitle());
        assertEquals(mockGoal.getDescription(), actualGoal.getDescription());
        assertEquals(mockGoal.isComplete(), actualGoal.isComplete());
        assertEquals(String.valueOf(mockGoal.getMProgress()), String.valueOf(actualGoal.getMProgress()));
    }

//  Bundle of local updates matches all Goals in the app.
    private <T extends AbstractGoal> void performCoreUpdates(T goal){
        goal.setTitle("Updated title");
        goal.setDescription("Updated description");
        goal.setMProgress(100.0D);
        goal.setComplete(true);
    }
}


