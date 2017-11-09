package io.sokolvault.wayofturtles.repositories.presence

interface PresenceRepository {

//  Create
    fun createNewTaskGoal()
    fun createNewJobGoal()
    fun createNewCompositeGoal()
    fun createNewTaskSubGoal(compositeGoalId: Int)
    fun createNewJobSubGoal(compositeGoalId: Int)

//  Delete
    fun deleteTaskGoal()
    fun deleteJobGoal()
    fun deleteCompositeGoal()
    fun deleteTaskSubGoal()
    fun deleteJobSubGoal()
}