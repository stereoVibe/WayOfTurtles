package io.sokolvault.wayofturtles.repositories.presence

import android.util.Log
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.data.db.model.CompositeGoalRoom
import io.sokolvault.wayofturtles.di.ApplicationContext
import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.model.JobGoal
import io.sokolvault.wayofturtles.model.TaskGoal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PresenceRepositoryData
@Inject constructor(roomDatabase: GoalsDatabase) :
        PresenceRepositoryContract {

    private val database: GoalsDatabase = roomDatabase

    override fun createNewTaskGoal(task: TaskGoal) {
    }

    override fun createNewJobGoal(jobGoal: JobGoal) {
    }

    override fun createNewCompositeGoal(compositeGoal: CompositeGoal) {
        Log.d("База", database.toString())
        val id = database.bigGoalDAO().insertBigGoal(CompositeGoalRoom(compositeGoal.title))
        Log.d("Номер цели", id.toString())
        val gettingGoal = database.bigGoalDAO().getBigGoalById(id.toInt()).toString()
        Log.d("Цель", gettingGoal.capitalize())
    }

    override fun createNewTaskSubGoal(compositeGoalId: Int) {
    }

    override fun createNewJobSubGoal(compositeGoalId: Int) {
    }

    override fun deleteTaskGoal() {
    }

    override fun deleteJobGoal() {
    }

    override fun deleteCompositeGoal(compositeGoal: CompositeGoal) {
    }

    override fun deleteTaskSubGoal() {
    }

    override fun deleteJobSubGoal() {
    }



}