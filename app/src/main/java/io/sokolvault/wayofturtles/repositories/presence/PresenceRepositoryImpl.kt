package io.sokolvault.wayofturtles.repositories.presence

import android.arch.lifecycle.LiveData
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.model.JobGoal
import io.sokolvault.wayofturtles.model.TaskGoal
import io.sokolvault.wayofturtles.repositories.presence.PresenceRepositoryContract
import javax.inject.Inject

class PresenceRepositoryImpl : PresenceRepositoryContract {

    @Inject
    lateinit var dbInstance: GoalsDatabase

    override fun createNewTaskGoal(task: TaskGoal) {
    }

    override fun createNewJobGoal(jobGoal: JobGoal) {
    }

    override fun createNewCompositeGoal(compositeGoal: CompositeGoal) {
        dbInstance.bigGoalDAO().insertBigGoal()
    }

    override fun createNewTaskSubGoal(compositeGoalId: Int) {
    }

    override fun createNewJobSubGoal(compositeGoalId: Int) {
    }

    override fun deleteTaskGoal() {
    }

    override fun deleteJobGoal() {
    }

    override fun deleteCompositeGoal() {
    }

    override fun deleteTaskSubGoal() {
    }

    override fun deleteJobSubGoal() {
    }
}