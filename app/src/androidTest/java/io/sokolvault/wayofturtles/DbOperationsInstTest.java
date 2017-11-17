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

import java.util.ArrayList;
import java.util.List;

import io.sokolvault.wayofturtles.carriers.DataJobSubGoal;
import io.sokolvault.wayofturtles.data.db.dao.CompositeGoalDAO;
import io.sokolvault.wayofturtles.data.db.GoalsDatabase;
import io.sokolvault.wayofturtles.data.db.dao.JobSubGoalDAO;
import io.sokolvault.wayofturtles.data.db.dao.TaskSubGoalDAO;
import io.sokolvault.wayofturtles.data.db.model.HybridGoalRoom;
import io.sokolvault.wayofturtles.data.db.model.JobSubGoalRoom;
import io.sokolvault.wayofturtles.data.db.model.MonotypeSubGoalRoom;
import io.sokolvault.wayofturtles.model.Goal;
import io.sokolvault.wayofturtles.model.xtensions.StepType;
import io.sokolvault.wayofturtles.model.xtensions.StepUnit;
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory;
import io.sokolvault.wayofturtles.model.xtensions.MoneyUnits;

@RunWith(AndroidJUnit4.class)
public class DbOperationsInstTest {

    private HybridGoalRoom BIG_GOAL =
            new HybridGoalRoom("title");

    private final JobSubGoalRoom JOB = new JobSubGoalRoom("Заголовок ",
            10, 1);

    private final DataJobSubGoal dataJOB = new DataJobSubGoal(1,
            "pfu",
            1,
            0,
            10,
            0,
            false, new StepUnit.Piece(1));

    private final MonotypeSubGoalRoom TASK =
            new MonotypeSubGoalRoom("Task", 1);


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
        JOB.setId(1);
        TASK.setId(1);
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

        HybridGoalRoom dbBigGoal = mCompositeGoalDAO.getAll().get(1);
        assertEquals(2, mCompositeGoalDAO.getAll().size());

        performCoreAsserts(BIG_GOAL, dbBigGoal);
    }

    @Test
    public void insertAndGetJobEntityTest(){

        JOB.getCounts().getUnitType();
        JOB.getCounts().getStep();

        mJobDAO.insertJobSubGoal(JOB);
        JobSubGoalRoom dbJob =
                getFirstGoalEntityAndAssertForSize(mJobDAO.getAll());

        performCoreAsserts(JOB, dbJob);
        assertEquals(JOB.getHybridGoalId(), dbJob.getHybridGoalId());
        assertEquals(JOB.getCycleQuantity(), dbJob.getCycleQuantity());
        assertEquals(JOB.getCompletedCycles(), dbJob.getCompletedCycles());
    }

    @Test
    public void insertAndGetTaskEntityTest(){
        mTaskDAO.insertTaskSubGoal(TASK);
        MonotypeSubGoalRoom dbTask =
                getFirstGoalEntityAndAssertForSize(mTaskDAO.getAll());
        performCoreAsserts(TASK, dbTask);
    }
//  UPDATE & READ
    @Test
    public void updateAndGetCompositeGoalRoomTest(){
        performCoreUpdates(BIG_GOAL);
        mCompositeGoalDAO.updateBigGoal(BIG_GOAL);

        HybridGoalRoom dbBigGoal = mCompositeGoalDAO.getAll().get(0);

        performCoreAsserts(BIG_GOAL, dbBigGoal);
    }

    @Test
    public void updateAndGetJobEntityTest(){
        /**
         * ID for BigGoal should be updated, as it matches the hybridGoalId in
         * all SubGoals (JobEntity here), so it must exist. Because of auto generated id for BigGoal,
         * we can't updated it in DB, so it needs another insert - {@link #addSecondBigGoal()}
        **/
        addSecondBigGoal();

        mJobDAO.insertJobSubGoal(JOB); // UPDATE needs something to update %)

        performCoreUpdates(JOB);
        JOB.setHybridGoalId(2);
        JOB.setCycleQuantity(20);
        JOB.setCompletedCycles(20);

        mJobDAO.updateJobSubGoal(JOB);

        JobSubGoalRoom dbJob =
                getFirstGoalEntityAndAssertForSize(mJobDAO.getAll());

        performCoreAsserts(JOB, dbJob);
        assertEquals(JOB.getHybridGoalId(), dbJob.getHybridGoalId());
        assertEquals(JOB.getCycleQuantity(), dbJob.getCycleQuantity());
        assertEquals(JOB.getCompletedCycles(), dbJob.getCompletedCycles());
    }

    @Test
    public void updateAndGetAndGetTaskEntityTest(){
        /**
         * ID for BigGoal should be updated, as it matches the hybridGoalId in
         * all SubGoals (TaskEntity here), so it must exist. Because of auto generated id for BigGoal,
         * we can't updated it in DB, so it needs another insert - {@link #addSecondBigGoal()}
         **/
        addSecondBigGoal();

        mTaskDAO.insertTaskSubGoal(TASK);

        performCoreUpdates(TASK);

        mTaskDAO.updateTaskSubGoal(TASK);
        MonotypeSubGoalRoom dbTask = getFirstGoalEntityAndAssertForSize(mTaskDAO.getAll());
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
        MonotypeSubGoalRoom taskEntity = mTaskDAO.getAll().get(0);
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
    private <T extends Goal> void performCoreAsserts(T mockGoal, T actualGoal){
        assertEquals(mockGoal.getId(), actualGoal.getId());
        assertEquals(mockGoal.getTitle(), actualGoal.getTitle());
        assertEquals(mockGoal.getDescription(), actualGoal.getDescription());
        assertEquals(mockGoal.isComplete(), actualGoal.isComplete());
        assertEquals(mockGoal.getGoalCategory(), actualGoal.getGoalCategory());
//        assertEquals(String.valueOf(mockGoal.getProgress()), String.valueOf(actualGoal.getProgress()));
    }

//  Bundle of local updates matches all Goals in the app.
    private <T extends Goal> void performCoreUpdates(T goal){
        goal.setTitle("Updated title");
        goal.setDescription("Updated description");
        goal.setGoalCategory(GoalCategory.FAMILY);
//        goal.setProgress(100.0D);
        goal.setComplete(true);
    }
}


