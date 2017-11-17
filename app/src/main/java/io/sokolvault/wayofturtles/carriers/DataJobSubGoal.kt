package io.sokolvault.wayofturtles.carriers

import io.sokolvault.wayofturtles.model.JobGoal
import io.sokolvault.wayofturtles.model.xtensions.Internable


data class DataJobSubGoal(override var id: Int,
                          override var title: String,
                          override val jobsQuantity: Int,
                          override val hybridGoalId: Int)
    :JobGoal(), Internable<JobGoal> {
    override var description: String = ""
}