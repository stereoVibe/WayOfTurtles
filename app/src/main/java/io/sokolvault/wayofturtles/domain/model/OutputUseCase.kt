package io.sokolvault.wayofturtles.domain.model

import io.sokolvault.wayofturtles.model.Goal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory

interface OutputUseCase<out G: Goal> {
    fun getGoalById(goalId: Int): G

    fun getGoalsFilteredByCategory(category: GoalCategory): List<G>
    fun getGoalsSortedByCreationDate(): List<G>
}