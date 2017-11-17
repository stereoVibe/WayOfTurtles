package io.sokolvault.wayofturtles.model.xtensions

import io.sokolvault.wayofturtles.model.MonotypeGoal

interface Compoundable<T: MonotypeGoal> {
    var subGoals: ArrayList<MonotypeGoal>
    var allSubGoalsQuantity: Int
    var completedSubGoalsQuantity: Int

    fun addSubGoalToList(internable: T)
    fun addAllSubGoals(internables: List<T>)

    fun countAllSubGoals(subGoals: List<MonotypeGoal>): Int =
            if (subGoals.isNotEmpty()) subGoals.count() else 0

    fun countCompletedSubGoals(subGoals: List<MonotypeGoal>): Int{
        return if (subGoals.isNotEmpty()) {
            subGoals.filter { it.isComplete }
                    .count()
        } else 0
    }
}