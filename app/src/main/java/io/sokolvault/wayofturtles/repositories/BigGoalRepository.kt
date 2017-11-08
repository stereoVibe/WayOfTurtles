package io.sokolvault.wayofturtles.repositories

import io.sokolvault.wayofturtles.model.complex.CompositeGoal
import io.sokolvault.wayofturtles.model.complex.SubGoal

interface BigGoalRepository : RepositoryContract<CompositeGoal, CompositeGoal> {

    fun <T: SubGoal> getSubGoals(): List<T>

}
