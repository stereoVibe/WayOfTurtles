package io.sokolvault.wayofturtles.domain.output

import io.sokolvault.wayofturtles.domain.model.OutputUseCase
import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory

class OutputCompositeGoalInteractor:OutputUseCase<CompositeGoal>{
    override fun getGoalById(goalId: Int): CompositeGoal {
        return this.getGoalById(goalId)
    }

    override fun getGoalsFilteredByCategory(category: GoalCategory): List<CompositeGoal> {
        return this.getGoalsFilteredByCategory(category)
    }

    override fun getGoalsSortedByCreationDate(): List<CompositeGoal> {
        return this.getGoalsSortedByCreationDate()
    }
}