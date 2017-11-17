package io.sokolvault.wayofturtles.repositories.presence

import io.sokolvault.wayofturtles.model.HybridGoal
import io.sokolvault.wayofturtles.model.JobGoal
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
    fun createNewCompositeGoal(hybridGoal: HybridGoal)
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
    fun deleteCompositeGoal(hybridGoal: HybridGoal)
    fun deleteTaskSubGoal()
    fun deleteJobSubGoal()
}