package io.sokolvault.wayofturtles;


import android.arch.lifecycle.ViewModelProviders;
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

import io.sokolvault.wayofturtles.data.db.dao.JobDAO;
import io.sokolvault.wayofturtles.data.db.dao.TaskDAO;
import io.sokolvault.wayofturtles.data.db.model.BigGoalEntity;
import io.sokolvault.wayofturtles.data.db.dao.BigGoalDAO;
import io.sokolvault.wayofturtles.data.db.GoalsDatabase;
import io.sokolvault.wayofturtles.data.db.model.JobEntity;
import io.sokolvault.wayofturtles.data.db.model.TaskEntity;
import io.sokolvault.wayofturtles.domain.model.BigGoal;
import io.sokolvault.wayofturtles.domain.model.Goal;
import io.sokolvault.wayofturtles.domain.model.GoalCategory;
import io.sokolvault.wayofturtles.ui.BigGoalViewModel;

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

    private final TaskEntity TASK =
            new TaskEntity("Task title", 1);


    private BigGoalDAO mBigGoalDAO;
    private JobDAO mJobDAO;
    private TaskDAO mTaskDAO;
    private GoalsDatabase mDatabase;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        mDatabase = Room.inMemoryDatabaseBuilder(context, GoalsDatabase.class).build();
        mBigGoalDAO = mDatabase.bigGoalDAO();
        mJobDAO = mDatabase.jobsDAO();
        mTaskDAO = mDatabase.taskDAO();

        BIG_GOAL.setId(1);
        mDatabase.bigGoalDAO().insertBigGoal(BIG_GOAL);
    }

    @After
    public void closeDb(){
        mDatabase.close();
    }

//    @Test
//    public void asyncWorkTest(){
//        BigGoal bigGoal = new BigGoal(1, "ttitle");
//        BigGoalViewModel bigGoalViewModel = ViewModelProviders
//                .of(this, ViewModelFactory(arrayMap))
//                .get(BigGoalViewModel::class.java)
//    }
//
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
        mTaskDAO.insertTaskSubGoal(TASK);
        TaskEntity dbTask =
                getFirstGoalEntityAndAssertForSize(mTaskDAO.getAll());
        performCoreAsserts(TASK, dbTask);
    }
//  UPDATE & READ
    @Test
    public void updateAndGetBigGoalEntityTest(){
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
        /**
         * ID for BigGoal should be updated, as it matches the compositeGoalID in
         * all SubGoals (TaskEntity here), so it must exist. Because of auto generated id for BigGoal,
         * we can't updated it in DB, so it needs another insert - {@link #addSecondBigGoal()}
         **/
        addSecondBigGoal();

        mTaskDAO.insertTaskSubGoal(TASK);

        performCoreUpdates(TASK);

        mTaskDAO.updateTaskSubGoal(TASK);
        TaskEntity dbTask = getFirstGoalEntityAndAssertForSize(mTaskDAO.getAll());
        performCoreAsserts(TASK, dbTask);
    }

//  DELETE & READ (failed to get)
    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteAndFailToGetBigGoalEntityTest(){
        mBigGoalDAO.delete(BIG_GOAL);
        assertNull(mBigGoalDAO.getAll().get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteAndFailToGetJobEntityTest(){
        JobEntity jobEntity = mJobDAO.getAll().get(0);
        mJobDAO.insertJobSubGoal(jobEntity);
        assertNull(mJobDAO.getAll().get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteAndFailToGetTaskEntityTest(){
        TaskEntity taskEntity = mTaskDAO.getAll().get(0);
        mTaskDAO.insertTaskSubGoal(taskEntity);
        assertNull(mTaskDAO.getAll().get(0));
    }

    private void addSecondBigGoal() {
        BIG_GOAL.setId(2); // setting new id for local variable
        mBigGoalDAO.insertBigGoal(BIG_GOAL);
        assertNotNull(mBigGoalDAO.getBigGoalById(2));
    }

    private <T extends Goal> T getFirstGoalEntityAndAssertForSize(List<T> goals){
        assertThat(goals.size(), is(1) );
        return goals.get(0);
    }

//  Bundle of asserts matches all Goals in the app.
    private <T extends Goal> void performCoreAsserts(T mockGoal, T actualGoal){
        assertEquals(mockGoal.getId(), actualGoal.getId());
        assertEquals(mockGoal.getTitle(), actualGoal.getTitle());
        assertEquals(mockGoal.getDescription(), actualGoal.getDescription());
        assertEquals(mockGoal.isComplete(), actualGoal.isComplete());
        assertEquals(mockGoal.getMGoalCategory(), actualGoal.getMGoalCategory());
        assertEquals(String.valueOf(mockGoal.getMProgress()), String.valueOf(actualGoal.getMProgress()));
    }

//  Bundle of local updates matches all Goals in the app.
    private <T extends Goal> void performCoreUpdates(T goal){
        goal.setTitle("Updated title");
        goal.setDescription("Updated description");
        goal.setMGoalCategory(GoalCategory.FAMILY);
        goal.setMProgress(100.0D);
        goal.setComplete(true);
    }
}


