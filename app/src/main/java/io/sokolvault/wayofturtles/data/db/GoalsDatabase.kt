package io.sokolvault.wayofturtles.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.data.db.dao.CompositeGoalDAO
import io.sokolvault.wayofturtles.data.db.dao.JobSubGoalDAO
import io.sokolvault.wayofturtles.data.db.dao.TaskSubGoalDAO

import io.sokolvault.wayofturtles.data.db.model.CompositeGoalRoom
import io.sokolvault.wayofturtles.data.db.model.JobSubGoalRoom
import io.sokolvault.wayofturtles.data.db.model.TaskSubGoalRoom
import io.sokolvault.wayofturtles.utils.Constants

@TypeConverters(AppTypeConverters::class)
@Database(entities = arrayOf(
        CompositeGoalRoom::class,
        JobSubGoalRoom::class,
        TaskSubGoalRoom::class), version = 1)
abstract class GoalsDatabase: RoomDatabase() {

    abstract fun bigGoalDAO(): CompositeGoalDAO
    abstract fun jobsDAO(): JobSubGoalDAO
    abstract fun taskDAO(): TaskSubGoalDAO

    companion object {
        private var sInstance: GoalsDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): GoalsDatabase{
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = Room.databaseBuilder(context.applicationContext,
                            GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
//                    Log.d(LOG_TAG, "New database creating")
                }
//                sInstance = Room.databaseBuilder(context.applicationContext,
//                        GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
            }
            return sInstance as GoalsDatabase
        }

        fun getInMemoryInstance(context: Context):GoalsDatabase{
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = Room.inMemoryDatabaseBuilder(context, GoalsDatabase::class.java).build()
//                    Log.d(LOG_TAG, "New database creating")
                }
//                sInstance = Room.databaseBuilder(context.applicationContext,
//                        GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
            }
            return sInstance as GoalsDatabase
        }
    }

//        private val LOG_TAG = GoalsDatabase::class.java.simpleName
//
//        private val LOCK = Any()
//        private lateinit var sInstance: GoalsDatabase
//
//        fun getInstance(context: Context): GoalsDatabase {
//            Log.d(LOG_TAG, "Getting the instance of database")
//
//            if (sInstance == null) {
//                synchronized(LOCK) {
//                    sInstance = Room.databaseBuilder(context.applicationContext,
//                            GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
////                    Log.d(LOG_TAG, "New database creating")
//                }
//                sInstance = Room.databaseBuilder(context.applicationContext,
//                        GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
//            }
//            return sInstance
//        }
    //    public abstract BGAllJobsDAO bgAllJobsDAO();
    //    public abstract SubGoalOfBigGoalDAO subGoalOfBigGoalDAO();
    //    public abstract BigGoalAllSubGoalsDAO bigGoalAllSubGoalsDAO();

}
