package io.sokolvault.wayofturtles.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log

import io.sokolvault.wayofturtles.model.BigGoal
import io.sokolvault.wayofturtles.model.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

@Database(entities = arrayOf(BigGoal::class, Job::class), version = 1)
abstract class GoalsDatabase : RoomDatabase() {

    fun clear() {
    }

    abstract fun bigGoalDAO(): BigGoalDAO
    abstract fun jobsDAO(): JobDAO
//    abstract fun taskDAO(): TaskDAO

    companion object {
        val DATABASE_NAME = "goals"
    }
//        private val LOG_TAG = GoalsDatabase::class.java.simpleName
//
//        private val LOCK = Any()
//        private lateinit var sInstance: GoalsDatabase

//        fun getInstance(context: Context): GoalsDatabase {
//            Log.d(LOG_TAG, "Getting the instance of database")
//
//            if (sInstance == null) {
////                synchronized(LOCK) {
////                    sInstance = Room.databaseBuilder(context.applicationContext,
////                            GoalsDatabase::class.java, GoalsDatabase.DATABASE_NAME).build()
////                    Log.d(LOG_TAG, "New database creating")
////                }
//                sInstance = Room.databaseBuilder(context.applicationContext,
//                        GoalsDatabase::class.java, GoalsDatabase.DATABASE_NAME).build()
//            }
//            return sInstance
//        }
    //    public abstract BGAllJobsDAO bgAllJobsDAO();
    //    public abstract SubGoalOfBigGoalDAO subGoalOfBigGoalDAO();
    //    public abstract BigGoalAllSubGoalsDAO bigGoalAllSubGoalsDAO();

}
