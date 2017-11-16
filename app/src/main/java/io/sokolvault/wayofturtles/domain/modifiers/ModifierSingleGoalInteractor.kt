package io.sokolvault.wayofturtles.domain.modifiers

import io.sokolvault.wayofturtles.domain.model.ModifierUseCase
import io.sokolvault.wayofturtles.model.SingleGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory

class ModifierSingleGoalInteractor: ModifierUseCase<SingleGoal> {
    override fun updateGoal(goal: SingleGoal) {
        return this.updateGoal(goal)
    }

    override fun updateGoals(vararg goals: SingleGoal) {
        return this.updateGoals(*goals)
    }

    override fun setCategory(goalId: Int, category: GoalCategory) {
        return this.setCategory(goalId, category)
    }

    override fun setCompleteStatus(goalId: Int, status: Boolean) {
        return this.setCompleteStatus(goalId, status)
    }

    override fun setProgress(goalId: Int, progress: Int) {
        return this.setProgress(goalId, progress)
    }
}