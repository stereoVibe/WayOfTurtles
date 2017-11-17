package io.sokolvault.wayofturtles.domain.output

import io.sokolvault.wayofturtles.domain.model.OutputUseCase
import io.sokolvault.wayofturtles.model.xtensions.Internable
import io.sokolvault.wayofturtles.model.MonotypeGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory

class OutputSubGoalInteractor:OutputUseCase<Internable<MonotypeGoal>> {
    override fun getGoalById(goalId: Int): Internable<MonotypeGoal> {
        return this.getGoalById(goalId)
    }

    override fun getGoalsFilteredByCategory(category: GoalCategory): List<Internable<MonotypeGoal>> {
        return this.getGoalsFilteredByCategory(category)
    }

    override fun getGoalsSortedByCreationDate(): List<Internable<MonotypeGoal>> {
        return this.getGoalsSortedByCreationDate()
    }

}