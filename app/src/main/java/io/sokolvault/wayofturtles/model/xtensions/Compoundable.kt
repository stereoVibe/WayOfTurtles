package io.sokolvault.wayofturtles.model.xtensions

import io.sokolvault.wayofturtles.model.Internable
import io.sokolvault.wayofturtles.model.SingleGoal

interface Compoundable<out T: SingleGoal>{
//    val subGoals: LinkedHashMap<Int, Internable<SingleGoal>>

    fun addSubGoalToList(internable: Internable<T>)
    fun addAllSubGoals(internables: List<Internable<T>>)

}