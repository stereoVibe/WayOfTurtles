package io.sokolvault.wayofturtles

import android.content.Context
import io.sokolvault.wayofturtles.db.BigGoal
import io.sokolvault.wayofturtles.db.BigGoalDAO
import io.sokolvault.wayofturtles.db.GoalsDatabase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
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
    private val LOCK = Any()

    @Before
    fun setUp() {
        mContext = ShadowApplication.getInstance().applicationContext
        mDatabase = GoalsDatabase.getInstance(mContext)
//        mBigGoalDAO = mock()
        mBigGoalDAO = mDatabase.bigGoalDAO()
    }

    @Test
    fun shouldInsertBigGoalIntoDBAndCheckForTitle() {
        val bigGoal = BigGoal("title")

        synchronized(LOCK) {
            Thread {
                var bigGoalsList: ArrayList<BigGoal>
                synchronized(LOCK) {
                    mBigGoalDAO.insertBigGoal(bigGoal)
                    bigGoalsList = mBigGoalDAO.all as ArrayList<BigGoal>
                    actual = bigGoalsList[bigGoalsList.size - 1].title
//                    TODO: This block below of Java concurrent way should be replaced by concurrent Kotlin library
                    (LOCK as java.lang.Object).notify()
                }
            }.start()
            //TODO: This block of Java concurrent way should be replaced by concurrent Kotlin library
            (LOCK as java.lang.Object).wait()
            assertEquals(bigGoal.title, actual)
        }
    }

    @Test
    fun subGoalShouldBeInsertedOnlyIfItsParentExist(){
        val subGoal = Job.newBuilder()
                .setTitle("title")
                .setCompositeGoalID(1)
                .setDescription("discr")
                .build()
    }
}