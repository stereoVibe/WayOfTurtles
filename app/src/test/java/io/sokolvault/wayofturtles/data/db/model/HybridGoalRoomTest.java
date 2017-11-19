package io.sokolvault.wayofturtles.data.db.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import io.sokolvault.wayofturtles.model.MonotypeGoal;
import io.sokolvault.wayofturtles.model.xtensions.Difficulty;
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory;

import static org.mockito.Mockito.*;

/**
 * Created by Vault on 18/11/2017.
 */
public class HybridGoalRoomTest {
    ArrayList<MonotypeGoal> arrayList = new ArrayList<>();

    @Mock
    Enum<GoalCategory> goalCategory;

    @InjectMocks
    MonotypeSubGoalRoom monotypeSubGoalRoom;
    @InjectMocks
    JobSubGoalRoom jobSubGoalRoom;
    @InjectMocks
    HybridGoalRoom hybridGoalRoom;
    @InjectMocks
    ArrayList<MonotypeGoal> subGoals = createArray();

    private ArrayList<MonotypeGoal> createArray(){
//        HybridGoalRoom hybridGoalRoom1 = new HybridGoalRoom();
//        hybridGoalRoom1.setId(1);
        MonotypeSubGoalRoom monotypeSubGoalRoom2 = new MonotypeSubGoalRoom();
        monotypeSubGoalRoom2.setId(1);
        monotypeSubGoalRoom2.setHybridGoalId(1);
//        monotypeSubGoalRoom2.setComplete(true);
        MonotypeSubGoalRoom monotypeSubGoalRoom1 = new MonotypeSubGoalRoom("title 2", 1);
        monotypeSubGoalRoom1.setId(4);
//        monotypeSubGoalRoom1.setComplete(true);
        JobSubGoalRoom jobSubGoalRoom = new JobSubGoalRoom("jobTitle", 10, 1);
        jobSubGoalRoom.setId(9);
        jobSubGoalRoom.setComplete(true);

        arrayList.add(monotypeSubGoalRoom1);
        arrayList.add(monotypeSubGoalRoom2);
        arrayList.add(jobSubGoalRoom);

        return arrayList;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        hybridGoalRoom.setId(1);
        hybridGoalRoom.addAllSubGoals(subGoals);
    }

    @Test
    public void testGetId() throws Exception {
        int result = hybridGoalRoom.getId();
        Assert.assertEquals(1, result);
    }

    @Test
    public void testSetId() throws Exception {
        hybridGoalRoom.setId(0);
    }

    @Test
    public void testGetDescription() throws Exception {
        String result = hybridGoalRoom.getDescription();
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    public void testSetDescription() throws Exception {
        hybridGoalRoom.setDescription("s");
    }

    @Test
    public void testIsComplete() throws Exception {
        hybridGoalRoom.setComplete(true);
        boolean result = hybridGoalRoom.isComplete();
        Assert.assertEquals(true, result);
    }

    @Test
    public void testSetComplete() throws Exception {
        hybridGoalRoom.setComplete(true);
    }

    @Test
    public void testGetGoalCategory() throws Exception {
        Enum<GoalCategory> result = hybridGoalRoom.getGoalCategory();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testSetGoalCategory() throws Exception {
        hybridGoalRoom.setGoalCategory(null);
    }

    @Test
    public void testGetProgress() throws Exception {
        double result = hybridGoalRoom.getProgress();
        Assert.assertEquals(((int) 0d), ((int) result));
    }

    @Test
    public void testSetProgress() throws Exception {
        hybridGoalRoom.setProgress(0d);
    }

    @Test
    public void testGetAllSubGoalsQuantity() throws Exception {
        int result = hybridGoalRoom.getAllSubGoalsQuantity();
        Assert.assertEquals(0, result);
    }

    @Test
    public void testSetAllSubGoalsQuantity() throws Exception {
        hybridGoalRoom.setAllSubGoalsQuantity(0);
    }

    @Test
    public void testGetCompletedSubGoalsQuantity() throws Exception {
        int result = hybridGoalRoom.getCompletedSubGoalsQuantity();
        Assert.assertEquals(0, result);
    }

    @Test
    public void testSetCompletedSubGoalsQuantity() throws Exception {
        hybridGoalRoom.setCompletedSubGoalsQuantity(0);
    }

    @Test
    public void testGetSubGoals() throws Exception {
        ArrayList<MonotypeGoal> result = hybridGoalRoom.getSubGoals();
        Assert.assertEquals(new ArrayList<MonotypeGoal>(Arrays.asList(new MonotypeSubGoalRoom("title", 0))), result);
    }

    @Test
    public void testSetSubGoals() throws Exception {
        hybridGoalRoom.setSubGoals(new ArrayList<MonotypeGoal>(Arrays.asList(new MonotypeSubGoalRoom("title", 0))));
    }

    @Test
    public void testAddSubGoalToList() throws Exception {
        hybridGoalRoom.addSubGoalToList(new MonotypeSubGoalRoom("title", 0));
    }

//    @Test
//    public void testAddAllSubGoals() throws Exception {
//        hybridGoalRoom.addAllSubGoals(Arrays.<? extends MonotypeGoal>asList(null));
//    }

    @Test
    public void testCalculateProgress() throws Exception {
        double result = hybridGoalRoom.calculateProgress();
        Assert.assertEquals(83.3, result, 0.2);
    }

    @Test
    public void testDefineDifficultyAndMap() throws Exception {
        monotypeSubGoalRoom.setId(2);
        ArrayList<MonotypeGoal> arrayList = new ArrayList<>();
        arrayList.add(0, monotypeSubGoalRoom);
        MonotypeSubGoalRoom monotypeSubGoalRoom1 = new MonotypeSubGoalRoom("title 2", 1);
        monotypeSubGoalRoom1.setId(4);
        arrayList.add(1, monotypeSubGoalRoom1);
        LinkedHashMap<Integer, Difficulty.Grade> result = hybridGoalRoom.defineDifficultyAndMap(arrayList);
        Assert.assertEquals(monotypeSubGoalRoom.getGrade(), result.get(2));
        Assert.assertEquals(monotypeSubGoalRoom1.getGrade(), result.get(4));
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void testGetTitle() throws Exception {
        String result = hybridGoalRoom.getTitle();
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    public void testSetTitle() throws Exception {
        hybridGoalRoom.setTitle("s");
    }

    @Test
    public void testComponent1() throws Exception {
        String result = hybridGoalRoom.component1();
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    public void testCopy() throws Exception {
        HybridGoalRoom result = hybridGoalRoom.copy("title");
        Assert.assertEquals(new HybridGoalRoom("title", "description"), result);
    }

    @Test
    public void testToString() throws Exception {
        String result = hybridGoalRoom.toString();
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    public void testHashCode() throws Exception {
        int result = hybridGoalRoom.hashCode();
        Assert.assertEquals(0, result);
    }

    @Test
    public void testEquals() throws Exception {
        boolean result = hybridGoalRoom.equals(null);
        Assert.assertEquals(true, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme