package io.sokolvault.wayofturtles.repositories.presence

/**
 * This @interface provides CREATE and DELETE operations
 * for interactors (use cases)
 * */
interface PresenceRepository {

    /**
     *
     * Use this methods:
     * [createNewTaskGoal],
     * [createNewJobGoal],
     * [createNewCompositeGoal],
     * [createNewTaskSubGoal],
     * [createNewJobSubGoal]
     * for CREATE operations
     * */
    fun createNewTaskGoal()
    fun createNewJobGoal()
    fun createNewCompositeGoal()
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