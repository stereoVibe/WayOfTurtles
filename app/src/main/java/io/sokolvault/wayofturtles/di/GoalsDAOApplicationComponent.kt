package io.sokolvault.wayofturtles.di

import dagger.Component
import io.sokolvault.wayofturtles.data.db.dao.CompositeGoalDAO
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.data.db.dao.JobSubGoalDAO
//import io.sokolvault.wayofturtles.data.db.dao.TaskDAO

@Component(modules = arrayOf(GoalsDAOModule::class))
interface GoalsDAOApplicationComponent {

    fun getDbInstance(): GoalsDatabase
    fun getBigGoalDao(): CompositeGoalDAO
    fun getJobDao(): JobSubGoalDAO
//    fun getTaskDao(): TaskDAO
}