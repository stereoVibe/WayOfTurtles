package io.sokolvault.wayofturtles

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.sokolvault.wayofturtles.data.db.dao.BigGoalDAO
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.data.db.dao.JobDAO
import io.sokolvault.wayofturtles.utils.Constants

@Module(includes = arrayOf(ContextModule::class))
class GoalsDAOModule {

    @Provides
    fun getDbInstance(context: Context): GoalsDatabase {
        return Room.databaseBuilder(context.applicationContext,
                GoalsDatabase::class.java, Constants.DATABASE_NAME).build()
//        return Room.inMemoryDatabaseBuilder(context.applicationContext,
//                GoalsDatabase::class.java).build()
    }

    @Provides
    fun getBigGoalDao(dbInstance: GoalsDatabase): BigGoalDAO {
        return dbInstance.bigGoalDAO()
    }

    @Provides
    fun getJobDao(dbInstance: GoalsDatabase): JobDAO {
        return dbInstance.jobsDAO()
    }

//    @Provides
//    fun getTaskDao(dbInstance: GoalsDatabase): TaskDAO{
//        return dbInstance.taskDAO()
//    }
}