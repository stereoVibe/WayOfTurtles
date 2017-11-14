package io.sokolvault.wayofturtles.repositories.presence

import android.arch.lifecycle.LiveData
import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.model.JobGoal
import io.sokolvault.wayofturtles.model.SingleGoal
import io.sokolvault.wayofturtles.model.TaskGoal

/**
 * This @interface provides CREATE and DELETE operations
 * for interactors (use cases)
 * */
interface PresenceRepositoryContract {

    /**
     *
     * Use this methods:
     *
     * [createNewTaskGoal],
     * [createNewJobGoal],
     * [createNewCompositeGoal],
     * [createNewTaskSubGoal],
     * [createNewJobSubGoal]
     * for CREATE operations
     * */
    fun createNewTaskGoal(task: TaskGoal)
    fun createNewJobGoal(jobGoal: JobGoal)
    fun createNewCompositeGoal(compositeGoal: CompositeGoal)
    fun createNewTaskSubGoal(compositeGoalId: Int)
    fun createNewJobSubGoal(compositeGoalId: Int)

    /**
     *
     * Use this methods:
     * [deleteTaskGoal],
     * [deleteJobGoal],
     * [deleteCompositeGoal],
     * [deleteTaskSubGoal],
     * [deleteJobSubGoal]
     * for DELETE operations
     * */
    fun deleteTaskGoal()
    fun deleteJobGoal()
    fun deleteCompositeGoal()
    fun deleteTaskSubGoal()
    fun deleteJobSubGoal()
}