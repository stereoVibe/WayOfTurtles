package io.sokolvault.wayofturtles.model.xtensions

import io.sokolvault.wayofturtles.model.complex.SubGoal
import io.sokolvault.wayofturtles.model.straight.SingleGoal


interface Compoundable<T: SingleGoal> {

    var subGoals: LinkedHashMap<Int, SubGoal<T>>

    fun addSubGoalToMap(subGoal: SubGoal<T>)
    fun addAllSubGoals(subGoals: Set<SubGoal<T>>)
    fun calculateSumProgress(): Int
}