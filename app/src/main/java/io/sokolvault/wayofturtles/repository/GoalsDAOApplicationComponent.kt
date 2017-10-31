package io.sokolvault.wayofturtles.repository

import dagger.Component
import io.sokolvault.wayofturtles.db.BigGoalDAO
import io.sokolvault.wayofturtles.db.JobDAO
//import io.sokolvault.wayofturtles.db.TaskDAO

@Component(modules = arrayOf(GoalsDAOModule::class))
interface GoalsDAOApplicationComponent {

    fun getBigGoalDao(): BigGoalDAO
    fun getJobDao(): JobDAO
//    fun getTaskDao(): TaskDAO
}