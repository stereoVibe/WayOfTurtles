package io.sokolvault.wayofturtles.repositories

import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.model.Internable
import io.sokolvault.wayofturtles.model.SingleGoal

interface BigGoalRepository : RepositoryContract<CompositeGoal, CompositeGoal> {

//    fun <T: Internable<SingleGoal>> getSubGoals(): List<T>

}
