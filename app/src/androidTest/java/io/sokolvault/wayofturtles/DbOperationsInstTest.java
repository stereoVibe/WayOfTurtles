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

import io.sokolvault.wayofturtles.data.db.dao.CompositeGoalDAO;
import io.sokolvault.wayofturtles.data.db.GoalsDatabase;
import io.sokolvault.wayofturtles.data.db.dao.JobSubGoalDAO;
import io.sokolvault.wayofturtles.data.db.dao.TaskSubGoalDAO;
import io.sokolvault.wayofturtles.data.db.model.CompositeGoalRoom;
import io.sokolvault.wayofturtles.data.db.model.JobSubGoalRoom;
import io.sokolvault.wayofturtles.data.db.model.TaskSubGoalRoom;
import io.sokolvault.wayofturtles.model.BaseGoal;
import io.sokolvault.wayofturtles.model.Goal;
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory;

@RunWith(AndroidJUnit4.class)
public class DbOperationsInstTest {

    private CompositeGoalRoom BIG_GOAL =
            new CompositeGoalRoom("title");

    private final JobSubGoalRoom JOB = new JobSubGoalRoom(1,
            "Заголовок ", 10, 1);
//    private final JobSubGoalRoom JOB = JobSubGoalRoom.newBuilder()
//            .setId(1)
//            .setCompositeGoalID(1)
//            .setTitle("Job Title")
//            .setDescription("Job description")
//            .setTasksQuantity(10)
//            .build();

    private final TaskSubGoalRoom TASK =
            new TaskSubGoalRoom(1, "Task", 1);


    private CompositeGoalDAO mCompositeGoalDAO;
    private JobSubGoalDAO mJobDAO;
    private TaskSubGoalDAO mTaskDAO;
    private GoalsDatabase mDatabase;

    @Before
    public void setUp() throws Exception {
        BIG_GOAL.setDescription("описание");
        JOB.setDescription("Описание");
        TASK.setDescription("Descript");
        Context context = InstrumentationRegistry.getTargetContext();
        mDatabase = Room.inMemoryDatabaseBuilder(context, GoalsDatabase.class).build();
        mCompositeGoalDAO = mDatabase.compositeGoalDAO();
        mJobDAO = mDatabase.jobsDAO();
        mTaskDAO = mDatabase.tasksDAO();

        BIG_GOAL.setId(1);
        mDatabase.compositeGoalDAO().insertBigGoal(BIG_GOAL);
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
    public void insertAndGetCompositeGoalRoomTest(){
        BIG_GOAL.setId(2); // Setting the ID to be sure it will match auto generating
        mCompositeGoalDAO.insertBigGoal(BIG_GOAL);

        CompositeGoalRoom dbBigGoal = mCompositeGoalDAO.getAll().get(1);
        assertEquals(2, mCompositeGoalDAO.getAll().size());

        performCoreAsserts(BIG_GOAL, dbBigGoal);
    }

    @Test
    public void insertAndGetJobEntityTest(){

        mJobDAO.insertJobSubGoal(JOB);
        JobSubGoalRoom dbJob =
                getFirstGoalEntityAndAssertForSize(mJobDAO.getAll());

        performCoreAsserts(JOB, dbJob);
        assertEquals(JOB.getCompositeGoalId(), dbJob.getCompositeGoalId());
        assertEquals(JOB.getJobsQuantity(), dbJob.getJobsQuantity());
        assertEquals(JOB.getCompletedJobs(), dbJob.getCompletedJobs());
    }

    @Test
    public void insertAndGetTaskEntityTest(){
        mTaskDAO.insertTaskSubGoal(TASK);
        TaskSubGoalRoom dbTask =
                getFirstGoalEntityAndAssertForSize(mTaskDAO.getAll());
        performCoreAsserts(TASK, dbTask);
    }
//  UPDATE & READ
    @Test
    public void updateAndGetCompositeGoalRoomTest(){
        performCoreUpdates(BIG_GOAL);
        mCompositeGoalDAO.updateBigGoal(BIG_GOAL);

        CompositeGoalRoom dbBigGoal = mCompositeGoalDAO.getAll().get(0);

        performCoreAsserts(BIG_GOAL, dbBigGoal);
    }

    @Test
    public void updateAndGetJobEntityTest(){
        /**
         * ID for BigGoal should be updated, as it matches the compositeGoalId in
         * all SubGoals (JobEntity here), so it must exist. Because of auto generated id for BigGoal,
         * we can't updated it in DB, so it needs another insert - {@link #addSecondBigGoal()}
        **/
        addSecondBigGoal();

        mJobDAO.insertJobSubGoal(JOB); // UPDATE needs something to update %)

        performCoreUpdates(JOB);
        JOB.setCompositeGoalId(2);
        JOB.setJobsQuantity(20);
        JOB.setCompletedJobs(20);

        mJobDAO.updateJobSubGoal(JOB);

        JobSubGoalRoom dbJob =
                getFirstGoalEntityAndAssertForSize(mJobDAO.getAll());

        performCoreAsserts(JOB, dbJob);
        assertEquals(JOB.getCompositeGoalId(), dbJob.getCompositeGoalId());
        assertEquals(JOB.getJobsQuantity(), dbJob.getJobsQuantity());
        assertEquals(JOB.getCompletedJobs(), dbJob.getCompletedJobs());
    }

    @Test
    public void updateAndGetAndGetTaskEntityTest(){
        /**
         * ID for BigGoal should be updated, as it matches the compositeGoalId in
         * all SubGoals (TaskEntity here), so it must exist. Because of auto generated id for BigGoal,
         * we can't updated it in DB, so it needs another insert - {@link #addSecondBigGoal()}
         **/
        addSecondBigGoal();

        mTaskDAO.insertTaskSubGoal(TASK);

        performCoreUpdates(TASK);

        mTaskDAO.updateTaskSubGoal(TASK);
        TaskSubGoalRoom dbTask = getFirstGoalEntityAndAssertForSize(mTaskDAO.getAll());
        performCoreAsserts(TASK, dbTask);
    }

//  DELETE & READ (failed to get)
    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteAndFailToGetCompositeGoalRoomTest(){
        mCompositeGoalDAO.delete(BIG_GOAL);
        assertNull(mCompositeGoalDAO.getAll().get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteAndFailToGetJobEntityTest(){
        JobSubGoalRoom jobSubGoalRoom = mJobDAO.getAll().get(0);
        mJobDAO.insertJobSubGoal(jobSubGoalRoom);
        assertNull(mJobDAO.getAll().get(0));
    }
//
    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteAndFailToGetTaskEntityTest(){
        TaskSubGoalRoom taskEntity = mTaskDAO.getAll().get(0);
        mTaskDAO.insertTaskSubGoal(taskEntity);
        assertNull(mTaskDAO.getAll().get(0));
    }

    private void addSecondBigGoal() {
        BIG_GOAL.setId(2); // setting new id for local variable
        mCompositeGoalDAO.insertBigGoal(BIG_GOAL);
        assertNotNull(mCompositeGoalDAO.getBigGoalById(2));
    }

    private <T extends Goal> T getFirstGoalEntityAndAssertForSize(List<T> goals){
        assertThat(goals.size(), is(1) );
        return goals.get(0);
    }

//  Bundle of asserts matches all Goals in the app.
    private <T extends BaseGoal> void performCoreAsserts(T mockGoal, T actualGoal){
        assertEquals(mockGoal.getId(), actualGoal.getId());
        assertEquals(mockGoal.getTitle(), actualGoal.getTitle());
        assertEquals(mockGoal.getDescription(), actualGoal.getDescription());
        assertEquals(mockGoal.isComplete(), actualGoal.isComplete());
        assertEquals(mockGoal.getGoalCategory(), actualGoal.getGoalCategory());
        assertEquals(String.valueOf(mockGoal.getProgress()), String.valueOf(actualGoal.getProgress()));
    }

//  Bundle of local updates matches all Goals in the app.
    private <T extends Goal> void performCoreUpdates(T goal){
        goal.setTitle("Updated title");
        goal.setDescription("Updated description");
        goal.setGoalCategory(GoalCategory.FAMILY);
        goal.setProgress(100.0D);
        goal.setComplete(true);
    }
}


