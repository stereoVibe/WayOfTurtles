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

import io.sokolvault.wayofturtles.data.db.model.HybridGoalRoom
import io.sokolvault.wayofturtles.data.db.model.JobSubGoalRoom
import io.sokolvault.wayofturtles.data.db.model.MonotypeSubGoalRoom
import io.sokolvault.wayofturtles.utils.Constants

@TypeConverters(AppTypeConverters::class)
@Database(entities = arrayOf(
        HybridGoalRoom::class,
        JobSubGoalRoom::class,
        MonotypeSubGoalRoom::class), version = 1)
abstract class GoalsDatabase : RoomDatabase() {

    abstract fun compositeGoalDAO(): CompositeGoalDAO
    abstract fun jobsDAO(): JobSubGoalDAO
    abstract fun tasksDAO(): TaskSubGoalDAO

    companion object {

        private lateinit var instance: GoalsDatabase

        fun getDbInstance(context: Context): GoalsDatabase {
            instance = Room.databaseBuilder(context.applicationContext,
                        GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
            return instance
        }

//        @Inject
//        fun getInMemoryInstance(@ApplicationContext provideContext: Context): GoalsDatabase {
//            instance = Room.inMemoryDatabaseBuilder(provideContext, GoalsDatabase::class.java).build()
//            return instance
//        }

//        @Inject
//        fun getInstance(@ApplicationContext provideContext: Context): GoalsDatabase =
//                Room.databaseBuilder(provideContext.applicationContext,
//                        GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
//        var sInstance: GoalsDatabase?
//        private val LOCK = Any()
//
//        @Inject
//        fun getInstance(@ApplicationContext provideContext: Context): GoalsDatabase{
//            if (sInstance == null) {
//                synchronized(LOCK) {
//                    sInstance = Room.databaseBuilder(provideContext.applicationContext,
//                            GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
////                    Log.d(LOG_TAG, "New database creating")
//                }
////                sInstance = Room.databaseBuilder(provideContext.applicationContext,
////                        GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
//            }
//            return sInstance as GoalsDatabase
//        }
//
//        @Inject
//        fun getInMemoryInstance(provideContext: Context) : GoalsDatabase{
//            if (sInstance == null) {
//                synchronized(LOCK) {
//                    sInstance = Room.inMemoryDatabaseBuilder(provideContext, GoalsDatabase::class.java).build()
////                    Log.d(LOG_TAG, "New database creating")
//                }
////                sInstance = Room.databaseBuilder(provideContext.applicationContext,
////                        GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
//            }
//            return sInstance as GoalsDatabase
//        }
    }

//        private val LOG_TAG = GoalsDatabase::class.java.simpleName
//
//        private val LOCK = Any()
//        private lateinit var sInstance: GoalsDatabase
//
//        fun getInstance(provideContext: Context): GoalsDatabase {
//            Log.d(LOG_TAG, "Getting the instance of database")
//
//            if (sInstance == null) {
//                synchronized(LOCK) {
//                    sInstance = Room.databaseBuilder(provideContext.applicationContext,
//                            GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
////                    Log.d(LOG_TAG, "New database creating")
//                }
//                sInstance = Room.databaseBuilder(provideContext.applicationContext,
//                        GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
//            }
//            return sInstance
//        }
    //    public abstract BGAllJobsDAO bgAllJobsDAO();
    //    public abstract SubGoalOfBigGoalDAO subGoalOfBigGoalDAO();
    //    public abstract BigGoalAllSubGoalsDAO bigGoalAllSubGoalsDAO();

}
