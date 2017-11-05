package io.sokolvault.wayofturtles

import dagger.Component
import io.sokolvault.wayofturtles.data.db.dao.BigGoalDAO
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.data.db.dao.JobDAO
//import io.sokolvault.wayofturtles.data.db.dao.TaskDAO

@Component(modules = arrayOf(GoalsDAOModule::class))
interface GoalsDAOApplicationComponent {

    fun getDbInstance(): GoalsDatabase
    fun getBigGoalDao(): BigGoalDAO
    fun getJobDao(): JobDAO
//    fun getTaskDao(): TaskDAO
}