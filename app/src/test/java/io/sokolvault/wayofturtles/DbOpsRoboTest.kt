package io.sokolvault.wayofturtles

import android.content.Context
import io.sokolvault.wayofturtles.Utils.DbOps
import io.sokolvault.wayofturtles.model.BigGoal
import io.sokolvault.wayofturtles.db.BigGoalDAO
import io.sokolvault.wayofturtles.db.GoalsDatabase
import io.sokolvault.wayofturtles.model.Job
import org.junit.After
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowApplication
import java.util.ArrayList

@Config(sdk = intArrayOf(26), manifest = "src/main/AndroidManifest.xml", shadows = arrayOf(ShadowApplication::class))
@RunWith(RobolectricTestRunner::class)
class DbOpsRoboTest {
    private lateinit var mContext: Context
    private lateinit var mBigGoalDAO: BigGoalDAO
    private lateinit var mDatabase: GoalsDatabase
    private lateinit var actual: String
    private lateinit var bigGoal: BigGoal
    private val LOCK = Any()
    private val LOCK2 = Any()

    @Before
    fun setUp() {
        bigGoal = BigGoal("title")
        mContext = ShadowApplication.getInstance().applicationContext
        mDatabase = GoalsDatabase.getInstance(mContext)
        mBigGoalDAO = mDatabase.bigGoalDAO()
    }

    @After
    fun tearDown() {
        mDatabase.clear()
    }

    @Test
    fun shouldInsertBigGoalIntoDBAndCheckForTitle() {
        synchronized(LOCK){
            DbOps.insertBigGoal(LOCK, bigGoal, mBigGoalDAO)
            (LOCK as java.lang.Object).wait()
            actual = bigGoal.title
            assertEquals("title", actual)
        }
    }

    @Test
    fun subGoalShouldBeInsertedOnlyIfItsParentExist(){
        var localBigGoalID: Int? = null

        synchronized(LOCK2){
            DbOps.insertBigGoal(LOCK2, bigGoal, mBigGoalDAO)
            (LOCK2 as java.lang.Object).wait()
            localBigGoalID = bigGoal.id
        }

        val subGoal = Job.newBuilder()
                .setTitle("title")
                .setCompositeGoalID(localBigGoalID!!)
                .setTasksQuantity(10)
                .setDescription("discr")
                .build()

        assertNotNull(subGoal.compositeGoalID)
        assertEquals(bigGoal.id, subGoal.compositeGoalID)
    }
}