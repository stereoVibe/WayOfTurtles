package io.sokolvault.wayofturtles.domain.output

import io.sokolvault.wayofturtles.domain.model.OutputUseCase
import io.sokolvault.wayofturtles.model.Internable
import io.sokolvault.wayofturtles.model.SingleGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory

class OutputSubGoalInteractor:OutputUseCase<Internable<SingleGoal>> {
    override fun getGoalById(goalId: Int): Internable<SingleGoal> {
        return this.getGoalById(goalId)
    }

    override fun getGoalsFilteredByCategory(category: GoalCategory): List<Internable<SingleGoal>> {
        return this.getGoalsFilteredByCategory(category)
    }

    override fun getGoalsSortedByCreationDate(): List<Internable<SingleGoal>> {
        return this.getGoalsSortedByCreationDate()
    }

}