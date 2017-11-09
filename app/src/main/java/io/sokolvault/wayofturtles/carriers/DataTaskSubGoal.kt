package io.sokolvault.wayofturtles.carriers

import io.sokolvault.wayofturtles.model.Internable
import io.sokolvault.wayofturtles.model.TaskGoal


data class DataTaskSubGoal(override var id: Int,
                           override var title: String,
                           override val compositeGoalId: Int):TaskGoal(), Internable<TaskGoal> {
    override var description: String = ""

    override fun isGoalOf(goal: TaskGoal): Boolean {
        return true
    }
}