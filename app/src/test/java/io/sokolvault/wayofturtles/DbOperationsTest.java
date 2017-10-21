package io.sokolvault.wayofturtles;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Environment;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockContext;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.sokolvault.wayofturtles.db.BigGoal;
import io.sokolvault.wayofturtles.db.GoalsDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DbOperationsTest {
//    private GoalsDatabase dbMock;

    @Mock
    Context mockContext;

    @Mock
    GoalsDatabase dbMock;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
//        mockContext = mock(Context.class);
        MockContext mockContext = new MockContext();
        mockContext.createPackageContext(mockContext.getPackageName(), Context.BIND_AUTO_CREATE);
        dbMock = Room.databaseBuilder(mockContext.getApplicationContext(),
                GoalsDatabase.class,GoalsDatabase.DATABASE_NAME)
                .build();
//        MockitoAnnotations.initMocks(this);
//        Context mockContext = mock(Context.class);
//        MockContext mockContext = new MockContext();
//        mockContext.createPackageContext("io.sokolvault.wayofturtles", 0);
//        dbMock = GoalsDatabase.getInstance(mockContext.getApplicationContext());
//        dbMock = GoalsDatabase.getInstance(mockContext.createDeviceProtectedStorageContext());
    }

    @Test
    public void shouldCreateEntityOfBigGoalTest(){
//        List<BigGoal> bigGoalList = dbMock.bigGoalDAO().getAll();
//        assertFalse(verify(bigGoalList).isEmpty());
////        dbMock.createBigGoalEntity()
        assertNull(dbMock);
    }

}
