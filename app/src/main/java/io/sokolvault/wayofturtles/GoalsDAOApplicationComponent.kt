package io.sokolvault.wayofturtles

import dagger.Component
import io.sokolvault.wayofturtles.data.db.BigGoalDAO
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.data.db.JobDAO
//import io.sokolvault.wayofturtles.data.db.TaskDAO

@Component(modules = arrayOf(GoalsDAOModule::class))
interface GoalsDAOApplicationComponent {

    fun getDbInstance(): GoalsDatabase
    fun getBigGoalDao(): BigGoalDAO
    fun getJobDao(): JobDAO
//    fun getTaskDao(): TaskDAO
}