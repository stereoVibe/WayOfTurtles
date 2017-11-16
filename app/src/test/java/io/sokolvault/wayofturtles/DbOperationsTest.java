package io.sokolvault.wayofturtles;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.MockitoJUnitRunner;

import io.sokolvault.wayofturtles.data.db.GoalsDatabase;
import io.sokolvault.wayofturtles.utils.Constants;

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
                GoalsDatabase.class, Constants.DATABASE_NAME)
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
//        List<BigGoal> bigGoalList = dbMock.compositeGoalDAO().getAll();
//        assertFalse(verify(bigGoalList).isEmpty());
////        dbMock.createBigGoalEntity()
        assertNull(dbMock);
    }

}
