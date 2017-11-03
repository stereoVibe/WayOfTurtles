package io.sokolvault.wayofturtles.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import io.sokolvault.wayofturtles.AppTypeConverters
import io.sokolvault.wayofturtles.GoalCategory

import io.sokolvault.wayofturtles.db.model.BigGoalEntity
import io.sokolvault.wayofturtles.db.model.JobEntity
import io.sokolvault.wayofturtles.db.model.TaskEntity

@TypeConverters(AppTypeConverters::class)
@Database(entities = arrayOf(BigGoalEntity::class,
        JobEntity::class,
        TaskEntity::class), version = 1)
abstract class GoalsDatabase : RoomDatabase() {

    abstract fun bigGoalDAO(): BigGoalDAO
    abstract fun jobsDAO(): JobDAO
    abstract fun taskDAO(): TaskDAO


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
