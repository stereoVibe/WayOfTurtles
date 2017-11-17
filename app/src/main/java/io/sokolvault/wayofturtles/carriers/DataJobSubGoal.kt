package io.sokolvault.wayofturtles.carriers

import io.sokolvault.wayofturtles.model.JobGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.model.xtensions.Internable
import io.sokolvault.wayofturtles.model.xtensions.StepUnit


data class DataJobSubGoal(override var id: Int,
                          override var title: String,
                          override val hybridGoalId: Int,
                          override var progress: Double,
                          override val cycleQuantity: Int,
                          override var completedCycles: Int,
                          override var isComplete: Boolean,
                          override val counts: StepUnit)
    :JobGoal(), Internable<JobGoal> {
    override fun calculateProgress(): Double {
        return this.calculateProgress()
    }

    override var goalCategory: Enum<GoalCategory> = GoalCategory.NONE
    override var description: String = ""
}