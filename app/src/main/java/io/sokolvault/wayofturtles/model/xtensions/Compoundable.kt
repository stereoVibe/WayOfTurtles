package io.sokolvault.wayofturtles.model.xtensions

import io.sokolvault.wayofturtles.model.MonotypeGoal

interface Compoundable<T: MonotypeGoal> {
    var subGoals: ArrayList<Internable<T>>
    var allSubGoalsQuantity: Int
    var completedSubGoalsQuantity: Int

    fun addSubGoalToList(internable: Internable<T>)
    fun addAllSubGoals(internables: List<Internable<T>>)

    fun countAllSubGoals(subGoals: ArrayList<Internable<T>>): Int =
            if (subGoals.isNotEmpty()) subGoals.count() else 0

    fun countCompletedSubGoals(subGoals: ArrayList<Internable<T>>): Int{
        return if (subGoals.isNotEmpty()) {
            subGoals.filterIsInstance<MonotypeGoal>()
                    .filter { it.isComplete }
                    .count()
        } else 0
    }
}