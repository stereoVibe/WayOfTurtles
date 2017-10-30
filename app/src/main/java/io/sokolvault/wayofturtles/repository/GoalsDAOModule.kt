package io.sokolvault.wayofturtles.repository

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.sokolvault.wayofturtles.ContextModule
import io.sokolvault.wayofturtles.db.BigGoalDAO
import io.sokolvault.wayofturtles.db.GoalsDatabase
import io.sokolvault.wayofturtles.db.JobDAO

@Module(includes = arrayOf(ContextModule::class))
class GoalsDAOModule {

    @Provides
    fun getDbInstance(context: Context): GoalsDatabase {
        return Room.databaseBuilder(context.applicationContext,
                GoalsDatabase::class.java, GoalsDatabase.DATABASE_NAME).build()
    }

    @Provides
    fun getBigGoalDao(dbInstance: GoalsDatabase): BigGoalDAO{
        return dbInstance.bigGoalDAO()
    }

    @Provides
    fun getJobDao(dbInstance: GoalsDatabase): JobDAO{
        return dbInstance.jobsDAO()
    }

//    @Provides
//    fun getTaskDao(dbInstance: GoalsDatabase): TaskDAO{
//        return dbInstance.taskDAO()
//    }
}