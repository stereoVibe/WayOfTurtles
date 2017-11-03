package io.sokolvault.wayofturtles;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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


    private final BigGoalEntity BIG_GOAL =
            new BigGoalEntity("title", "description");

    private final JobEntity JOB = JobEntity.newBuilder()
            .setId(1)
            .setCompositeGoalID(1)
            .setTitle("Job Title")
            .setDescription("Job description")
            .setTasksQuantity(10)
            .build();

    private BigGoalEntity mBigGoal;
    private BigGoalDAO mBigGoalDAO;
    private JobDAO mJobDAO;
    private GoalsDatabase mDatabase;

    @Before
    public void setUp() throws Exception {
        BIG_GOAL.setId(1);
        Context context = InstrumentationRegistry.getTargetContext();
        mDatabase = Room.inMemoryDatabaseBuilder(context, GoalsDatabase.class).build();
        mBigGoalDAO = mDatabase.bigGoalDAO();
        mJobDAO = mDatabase.jobsDAO();
    }

    @After
    public void closeDb(){
        mDatabase.close();
    }

//    CRUD tests

//  INSERT & READ tests
    @Test
    public void insertAndGetBigGoalEntityTest(){
        mDatabase.bigGoalDAO().insertBigGoal(BIG_GOAL);

        BigGoalEntity dbBigGoal
                = getFirstGoalEntityAndAssertForSize(mDatabase.bigGoalDAO().getAll());

        performCoreAsserts(BIG_GOAL, dbBigGoal);
    }

    @Test
    public void insertAndGetJobEntityTest(){
        mDatabase.bigGoalDAO().insertBigGoal(BIG_GOAL);
        assertEquals(1, mDatabase.bigGoalDAO().getAll().size());

        mDatabase.jobsDAO().insertJobSubGoal(JOB);
        JobEntity dbJob =
                getFirstGoalEntityAndAssertForSize(mDatabase.jobsDAO().getAll());

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
        mDatabase.bigGoalDAO().insertBigGoal(BIG_GOAL);

        performCoreUpdates(BIG_GOAL);
        mDatabase.bigGoalDAO().updateBigGoal(BIG_GOAL);

        BigGoalEntity dbBigGoal =
                getFirstGoalEntityAndAssertForSize(mDatabase.bigGoalDAO().getAll());

        performCoreAsserts(BIG_GOAL, dbBigGoal);
    }

    @Test
    public void updateAndGetJobEntityTest(){
        BIG_GOAL.setId(2);
        JOB.setCompositeGoalID(2);

        assertEquals(2, BIG_GOAL.getId());
        mDatabase.bigGoalDAO().insertBigGoal(BIG_GOAL);
        assertEquals(1, mDatabase.bigGoalDAO().getAll().size());

        mDatabase.jobsDAO().insertJobSubGoal(JOB);
        assertEquals(1, mDatabase.jobsDAO().getAll().size());

        performCoreUpdates(JOB);
        JOB.setTasksQuantity(20);
        JOB.setCompletedTasksQuantity(20);

        mDatabase.jobsDAO().updateJob(JOB);

        JobEntity dbJob =
                getFirstGoalEntityAndAssertForSize(mDatabase.jobsDAO().getAll());

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



//    @Test
//    public void writeBigGoal(){
//        mBigGoalDAO.insertBigGoal(mBigGoal);
//        assertNotNull(mBigGoalDAO.getAll());
//    }
//
////    @Test
////    public void insertJob(){
////        Job job;
////        mBigGoalDAO.insertBigGoal(mBigGoal);
////        if (mBigGoal.getId() != null) {
////            job = Job.newBuilder().
////                    setTitle("testTitle")
////                    .setCompositeGoalID(mBigGoal.getId())
////                    .setTasksQuantity(10)
////                    .build();
////            mJobDAO.insertJobSubGoal(job);
////        }
////        assertNotNull(mJobDAO.getAll());
////    }
//
//    @Test
//    public void insertingTest(){
//        JobEntity job;
//        int bigGoalId;
//        List<JobEntity> jobsList;
//        mBigGoalDAO.insertBigGoal(mBigGoal);
//        mBigGoalDAO.insertBigGoal(mBigGoal);
//        mBigGoal = mBigGoalDAO.getBigGoalById(2);
//        bigGoalId = mBigGoal.getId();
//        job = JobEntity.newBuilder()
//                .setId(0)
//                .setTitle("test")
//                .setCompositeGoalID(bigGoalId)
//                .setTasksQuantity(10)
//                .build();
//        mJobDAO.insertJobSubGoal(job);
//        jobsList = mJobDAO.getAll();
//
//        assertEquals(bigGoalId, jobsList.get(0).getCompositeGoalID());
////        assertEquals(2, mBigGoalDAO.getAll().size());
//    }

    private <T extends AbstractGoal> T getFirstGoalEntityAndAssertForSize(List<T> goals){
        assertThat(goals.size(), is(1) );
        return goals.get(0);
    }

    private <T extends AbstractGoal> void performCoreAsserts(T mockGoal, T actualGoal){
        assertEquals(mockGoal.getId(), actualGoal.getId());
        assertEquals(mockGoal.getTitle(), actualGoal.getTitle());
        assertEquals(mockGoal.getDescription(), actualGoal.getDescription());
        assertEquals(mockGoal.isComplete(), actualGoal.isComplete());
        assertEquals(String.valueOf(mockGoal.getMProgress()), String.valueOf(actualGoal.getMProgress()));
    }

    private <T extends AbstractGoal> void performCoreUpdates(T goal){
        goal.setTitle("Updated title");
        goal.setDescription("Updated description");
        goal.setMProgress(100.0D);
        goal.setComplete(true);
    }
}


