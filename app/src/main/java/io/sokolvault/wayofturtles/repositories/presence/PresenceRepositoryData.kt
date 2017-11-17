package io.sokolvault.wayofturtles.repositories.presence

import android.util.Log
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.data.db.model.HybridGoalRoom
import io.sokolvault.wayofturtles.model.HybridGoal
import io.sokolvault.wayofturtles.model.JobGoal
import io.sokolvault.wayofturtles.model.MonotypeGoal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PresenceRepositoryData
@Inject constructor(roomDatabase: GoalsDatabase) :
        PresenceRepositoryContract {

    private val database: GoalsDatabase = roomDatabase

    override fun createNewTaskGoal(monotype: MonotypeGoal) {
    }

    override fun createNewJobGoal(jobGoal: JobGoal) {
    }

    override fun createNewCompositeGoal(hybridGoal: HybridGoal) {
        Log.d("База", database.toString())
        val id = database.compositeGoalDAO().insertBigGoal(HybridGoalRoom(hybridGoal.title))
        Log.d("Номер цели", id.toString())
        val gettingGoal = database.compositeGoalDAO().getBigGoalById(id.toInt()).toString()
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

    override fun deleteCompositeGoal(hybridGoal: HybridGoal) {
    }

    override fun deleteTaskSubGoal() {
    }

    override fun deleteJobSubGoal() {
    }



}