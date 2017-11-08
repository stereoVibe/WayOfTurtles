//package io.sokolvault.wayofturtles
//
//import android.content.Context
//import io.sokolvault.wayofturtles.utils.DbOps
//import io.sokolvault.wayofturtles.model.BigGoal
//import io.sokolvault.wayofturtles.data.db.BigGoalDAO
//import io.sokolvault.wayofturtles.data.db.GoalsDatabase
//import io.sokolvault.wayofturtles.data.db.dao.JobDAO
//import io.sokolvault.wayofturtles.model.Job
//import org.junit.After
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.robolectric.RobolectricTestRunner
//import org.robolectric.annotation.Config
//import org.robolectric.shadows.ShadowApplication
//
//@Config(sdk = intArrayOf(26), manifest = "src/main/AndroidManifest.xml", shadows = arrayOf(ShadowApplication::class))
//@RunWith(RobolectricTestRunner::class)
//class DbOpsRoboTest {
//    private lateinit var mContext: Context
//
//    private lateinit var mBigGoalDAO: BigGoalDAO
//    private lateinit var mJobDAO: JobDAO
//
//    private lateinit var mDatabase: GoalsDatabase
//
//    private lateinit var actual: String
//    private lateinit var bigGoal: BigGoal
//    private val LOCK = Any()
//    private val LOCK2 = Any()
//
//    @Before
//    fun setUp() {
//        bigGoal = BigGoal("title")
//        mContext = ShadowApplication.getInstance().applicationContext
//        mDatabase = GoalsDatabase.getInstance(mContext)
//        mBigGoalDAO = mDatabase.bigGoalDAO()
//        mJobDAO = mDatabase.jobsDAO()
//    }
//
//    @After
//    fun tearDown() {
//        if (mDatabase.isOpen){
//            mDatabase.close()
//        }
//        mDatabase.clear()
//    }
//
//    @Test
//    fun shouldInsertBigGoalIntoDBAndCheckForTitle() {
//        synchronized(LOCK){
//            DbOps.insertBigGoal(LOCK, bigGoal, mBigGoalDAO)
//            (LOCK as java.lang.Object).wait()
//            actual = bigGoal.title
//            assertEquals("title", actual)
//        }
//    }
//
//    @Test
//    fun shouldCheckValidityOfAssignmentSubGoalToBigGoalByCheckingCompositeID(){
//        var localBigGoalID: Int? = null
//
//        synchronized(LOCK2){
//            DbOps.insertBigGoal(LOCK2, bigGoal, mBigGoalDAO)
//            (LOCK2 as java.lang.Object).wait()
//            localBigGoalID = bigGoal.id
//        }
//
//        val subGoal = Job.newBuilder()
//                .setTitle("title")
//                .setmCompositeGoalID(localBigGoalID!!)
//                .setTasksQuantity(10)
//                .setDescription("discr")
//                .build()
//
////        assertNotNull(subGoal.compositeGoalId)
//        assertEquals(localBigGoalID, subGoal.compositeGoalId)
//    }
//
//    @Test
//    fun shouldInsertSubGoalIfAllRequirementsCorrect(){
//        var localBigGoalID: Int?
//        var jobsList: List<Job>? = null
//
//        synchronized(LOCK){
//            DbOps.insertBigGoal(LOCK, bigGoal, mBigGoalDAO)
//            (LOCK as java.lang.Object).wait()
//            DbOps.insertBigGoal(LOCK, bigGoal, mBigGoalDAO)
//            (LOCK as java.lang.Object).wait()
//
////            bigGoal = mBigGoalDAO.getBigGoalById(1)
//
//            localBigGoalID = bigGoal.id
//            val subGoal = Job.newBuilder()
//                    .setId(0)
//                    .setTitle("title")
//                    .setmCompositeGoalID(localBigGoalID!!)
//                    .setTasksQuantity(10)
//                    .setDescription("discr")
//                    .build()
//
//            DbOps.insertSubGoal(LOCK, subGoal, mJobDAO)
//            (LOCK).wait()
//
//            Thread {
//                synchronized(LOCK) {
//                    jobsList = mJobDAO.getAll()
//                    (LOCK as java.lang.Object).notify()
//                }
//            }.start()
//            (LOCK as java.lang.Object).wait()
////            assertNotNull(jobsList)
//            assertEquals(localBigGoalID, jobsList!![0].compositeGoalId)
//        }
//    }
//
////    @Test
////    fun shouldAssignAllInsertedSubgoalsToAppropriateBigGoal(){
////        var localBigGoalID: Int?
////        var jobsList: List<Job>? = null
////        var listToInsert: ArrayList<Job> = ArrayList()
////
////        synchronized(LOCK2) {
////            DbOps.insertBigGoal(LOCK2, bigGoal, mBigGoalDAO)
////            (LOCK2 as java.lang.Object).wait()
////            localBigGoalID = bigGoal.id
////            var subGoal: Job? = null
////
////            for (i in 1..5) {
////                Thread{
////                    synchronized(LOCK2) {
////                        subGoal = Job.newBuilder()
////                                .setId(i)
////                                .setTitle("title " + i)
////                                .setmCompositeGoalID(localBigGoalID!!)
////                                .setTasksQuantity(i + 1)
////                                .setDescription("discr")
////                                .build()
////                        (LOCK2 as java.lang.Object).notify()
////                    }
////                }.start()
////                (LOCK2 as java.lang.Object).wait()
////
////                DbOps.insertJobSubGoal(LOCK2, subGoal!!,mJobDAO)
////                (LOCK2 as java.lang.Object).wait()
////            }
////
//////            DbOps.insertBulkSubGoals(LOCK2, listToInsert as List<Job>, mJobDAO)
//////            (LOCK2 as java.lang.Object).wait()
////
////            Thread {
////                synchronized(LOCK2) {
////                    jobsList = mJobDAO.getAll()
////                    (LOCK2 as java.lang.Object).notify()
////                }
////            }.start()
////            (LOCK2 as java.lang.Object).wait()
////
////            for (i in 1..5) {
////                assertEquals(localBigGoalID, jobsList!![i-1].compositeGoalId)
////            }
////        }
////
////    }
//
////    @Test
////    fun shouldBulkInsertOfSubGoals(){
////
////    }
//
//}