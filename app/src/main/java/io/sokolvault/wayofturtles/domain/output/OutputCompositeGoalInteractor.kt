package io.sokolvault.wayofturtles.domain.output

import io.sokolvault.wayofturtles.domain.model.OutputUseCase
import io.sokolvault.wayofturtles.model.HybridGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory

class OutputCompositeGoalInteractor:OutputUseCase<HybridGoal>{
    override fun getGoalById(goalId: Int): HybridGoal {
        return this.getGoalById(goalId)
    }

    override fun getGoalsFilteredByCategory(category: GoalCategory): List<HybridGoal> {
        return this.getGoalsFilteredByCategory(category)
    }

    override fun getGoalsSortedByCreationDate(): List<HybridGoal> {
        return this.getGoalsSortedByCreationDate()
    }
}