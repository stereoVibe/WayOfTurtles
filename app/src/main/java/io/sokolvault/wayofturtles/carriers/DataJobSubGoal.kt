package io.sokolvault.wayofturtles.carriers

import io.sokolvault.wayofturtles.model.JobGoal
import io.sokolvault.wayofturtles.model.Internable


data class DataJobSubGoal(override var id: Int,
                          override var title: String,
                          override val jobsQuantity: Int,
                          override val compositeGoalId: Int)
    :JobGoal(), Internable<JobGoal>{
    override var description: String = ""

    override fun isGoalOf(goal: JobGoal): Boolean {
        return goal::class.isInstance(this)
    }
}