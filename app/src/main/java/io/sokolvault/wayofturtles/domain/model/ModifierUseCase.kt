package io.sokolvault.wayofturtles.domain.model

import io.sokolvault.wayofturtles.model.Goal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory

interface ModifierUseCase<in G: Goal> {
    fun updateGoal(goal: G)
    fun updateGoals(vararg goals: G)
    fun setCategory(goalId: Int, category: GoalCategory)
    fun setCompleteStatus(goalId: Int, status: Boolean)
    fun setProgress(goalId: Int, progress: Int)
}